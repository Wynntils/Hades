package com.wynntils.hades.protocol.builders;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wynntils.hades.manager.HadesNetworkManager;
import com.wynntils.hades.protocol.enums.PacketDirection;
import com.wynntils.hades.protocol.interfaces.IHadesConnection;
import com.wynntils.hades.protocol.interfaces.HadesHandlerFactory;
import com.wynntils.hades.protocol.interfaces.IHadesServerContainer;
import com.wynntils.hades.protocol.io.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.Inet6Address;
import java.net.InetAddress;

public class HadesNetworkBuilder {

    // mutual
    PacketDirection direction;
    InetAddress address;
    int serverPort = 0;
    int compressionThreshold = 0;
    IHadesConnection handler;

    // server
    HadesHandlerFactory handlerFactory;
    IHadesServerContainer serverContainer;

    /**
     * Remember: this is the packet DIRECTION not the packet origin
     * So CLIENT stands for SENDING A PACKET FORWARDS A CLIENT (aka it's the server)
     *
     * Used on creating: SERVER; CLIENT.
     *
     * @param direction the provided direction
     * @return the builder instance
     */
    public HadesNetworkBuilder setDirection(PacketDirection direction) {
        this.direction = direction;

        return this;
    }

    /**
     * Set the binding server address
     *
     * Used on creating: SERVER; CLIENT.
     *
     * @param address the server address
     * @param serverPort the server port
     * @return the builder instance
     */
    public HadesNetworkBuilder setAddress(InetAddress address, int serverPort) {
        this.address = address;
        this.serverPort = serverPort;

        return this;
    }

    /**
     * Set the compression threshold for compressing packets using ZLib
     * Reminder: this needs to be the same for the client and the server otherwise
     * errors will be throw since the packets will lose their headers.
     *
     * Used on creating: SERVER; CLIENT.
     *
     * @param compressionThreshold the byte threshold amount
     * @return the builder instance
     */
    public HadesNetworkBuilder setCompressionThreshold(int compressionThreshold) {
        this.compressionThreshold = compressionThreshold;

        return this;
    }

    /**
     * Represents the client packet handler.
     * @see com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter
     *
     * Used on creating: CLIENT.
     *
     * @param handler the handler itself.
     * @return the builder instance
     */
    public HadesNetworkBuilder setHandler(IHadesConnection handler) {
        this.handler = handler;

        return this;
    }

    /**
     * Represents the server packet handler factory.
     * @see com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter
     * @see HadesHandlerFactory
     *
     * Used on creating: SERVER.
     *
     * @param handlerFactory the handler factory itself.
     * @return the builder instance
     */
    public HadesNetworkBuilder setHandlerFactory(HadesHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;

        return this;
    }

    /**
     * Determines the server container that will be used for registering available connections
     * @see IHadesServerContainer
     *
     * Used on creating: SERVER.
     *
     * @param serverContainer the container itself
     * @return the builder instance
     */
    public HadesNetworkBuilder setServerContainer(IHadesServerContainer serverContainer) {
        this.serverContainer = serverContainer;

        return this;
    }

    private EventLoopGroup getEventLoopGroup() {
        return Epoll.isAvailable()
                ? new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Hades Epoll IO #%d").setDaemon(true).build())
                : new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Hades IO #%d").setDaemon(true).build());
    }

    private Class <? extends SocketChannel> getClientChannel() {
        return Epoll.isAvailable() ? EpollSocketChannel.class : NioSocketChannel.class;
    }

    private Class <? extends ServerSocketChannel> getServerChannel() {
        return Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }

    private void setupChannel(Channel ch, HadesNetworkManager manager) {
        ch.config().setOption(ChannelOption.TCP_NODELAY, true);
        ch.config().setOption(ChannelOption.SO_KEEPALIVE, true);

        if (this.direction == PacketDirection.CLIENT) {
            ch.pipeline().addLast("ping", new IdleStateHandler(5, 0, 0));
        } else {
            ch.pipeline().addLast("ping", new IdleStateHandler(0, 4, 0));
        }

        ch.pipeline().addLast("splitter", new HadesIntSplitter());
        ch.pipeline().addLast("decoder", new HadesPacketDecoder(direction.getDecodeRegistry()));
        ch.pipeline().addLast("prepender", new HadesIntPrepender());
        ch.pipeline().addLast("encoder", new HadesPacketEncoder(direction.getEncodeRegistry()));
        ch.pipeline().addLast("handler", manager);

        // packet compression
        if (compressionThreshold != 0) {
            ch.pipeline().addBefore("decoder", "decompress", new HadesCompressionDecoder(compressionThreshold));
            ch.pipeline().addBefore("encoder", "compress", new HadesCompressionEncoder(compressionThreshold));
        }
    }

    /**
     * Bake up and prepares the client connection forwards a Hades Server.
     * @see HadesNetworkManager
     *
     * @return the client NetworkManager.
     */
    public HadesNetworkManager buildClient() {
        assert direction != null && handler != null && direction == PacketDirection.SERVER;

        if (address instanceof Inet6Address) System.setProperty("java.net.preferIPv4Stack", "false");
        HadesNetworkManager manager = new HadesNetworkManager(direction, handler);

        // starting the client channel
        new Bootstrap().group(getEventLoopGroup()).handler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel ch) {
                setupChannel(ch, manager);
            }
        }).channel(getClientChannel()).connect(address, serverPort).syncUninterruptibly();

        return manager;
    }

    /**
     * Bake up and starts the Hades server.
     *
     * @return the server container
     */
    public IHadesServerContainer buildServer() {
        assert serverContainer != null && address != null && serverPort != 0 && direction == PacketDirection.CLIENT;

        // setup the server network
        new ServerBootstrap().group(getEventLoopGroup()).channel(getServerChannel()).childHandler(new ChannelInitializer<Channel>() {
            protected void initChannel(Channel ch) throws Exception {
                setupChannel(ch, new HadesNetworkManager(PacketDirection.CLIENT, handlerFactory.createHandler()));
            }
        }).localAddress(address, serverPort).bind().syncUninterruptibly();

        return serverContainer;
    }

}
