package com.wynntils.hades.objects;

import com.wynntils.hades.protocol.enums.PacketDirection;
import com.wynntils.hades.protocol.interfaces.HadesHandlerFactory;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.IHadesAdapter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This represents the connection.
 *
 * If direction is SERVER it represents the client connection to the server
 * If direction is CLIENT it represents the server channel of the client
 *
 * In order to create a NetworkManager use
 * @see com.wynntils.hades.protocol.builders.HadesNetworkBuilder
 */
public class HadesConnection extends SimpleChannelInboundHandler<HadesPacket<?>> {

    PacketDirection direction;
    Channel channel;

    Future<?> autoFlusher;
    IHadesAdapter packetListener;

    public HadesConnection(PacketDirection direction, HadesHandlerFactory handlerFactory) {
        this.direction = direction;
        this.packetListener = handlerFactory.createHandler(this);
    }

    /**
     * Sends a packet forwards the channel
     * @see HadesPacket
     *
     * @param packet the packet itself
     */
    public void sendPacket(HadesPacket packet) {
        if (channel == null || !channel.isOpen()) return;

        // assures the packet send is inside the event loop thread
        if (!channel.eventLoop().inEventLoop()) {
            channel.eventLoop().execute(() -> channel.write(packet));
            return;
        }

        channel.write(packet);
    }

    /**
     * Sends a packet forwards the channel and flushes the channel
     * @see HadesPacket
     *
     * @param packet the packet itself
     */
    public void sendPacketAndFlush(HadesPacket packet) {
        if (channel == null || !channel.isOpen()) return;

        // assures the packet send is inside the event loop thread
        if (!channel.eventLoop().inEventLoop()) {
            channel.eventLoop().execute(() -> channel.writeAndFlush(packet));
            return;
        }

        channel.writeAndFlush(packet);
    }

    /**
     * Closes the provided channel
     */
    public void disconnect() {
        if (channel == null || !channel.isOpen()) {
            return;
        }

        channel.close().awaitUninterruptibly();
    }

    /**
     * Should be called in order to receive and update the packet queue
     */
    public void flushPackets() {
        if (channel == null || !channel.isOpen()) {
            return;
        }

        channel.flush();
    }

    /**
     * @return if the channel is open
     */
    public boolean isOpen() {
        return channel != null && channel.isOpen();
    }

    /**
     * @return channel ip address
     */
    public String getIp() {
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();
        return address.getAddress().getHostAddress();
    }

    /**
     * Receives the channel when it's ready to be used
     * Updates this connection channel and starts the auto flushe
     *
     * @param ctx the context
     * @throws Exception if failed
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.channel = ctx.channel();
        this.autoFlusher = channel.eventLoop().scheduleAtFixedRate(() -> channel.flush(), 25, 25, TimeUnit.MILLISECONDS);
        packetListener.onConnect();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (autoFlusher != null) autoFlusher.cancel(true);
        packetListener.onDisconnect();
    }

    /**
     * Process the packet over the HadesConnection
     * @see com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter
     * @see com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter
     *
     * @param ctx the channel context
     * @param msg the packet instance
     * @throws Exception if failed
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HadesPacket<?> msg) throws Exception {
        if (channel == null || !channel.isOpen() || packetListener == null) return;

        ((HadesPacket<IHadesAdapter>) msg).process(packetListener); // process the packet
    }

    /**
     * Dispatches every exception
     *
     * @param ctx the channel context
     * @param cause the throwable
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        cause.printStackTrace();
    }

}
