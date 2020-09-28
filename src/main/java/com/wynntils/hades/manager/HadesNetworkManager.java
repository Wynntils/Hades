package com.wynntils.hades.manager;

import com.wynntils.hades.protocol.enums.PacketDirection;
import com.wynntils.hades.protocol.interfaces.IHadesConnection;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import io.netty.channel.*;

/**
 * This represents the connection.
 *
 * If direction is SERVER it represents the client connection to the server
 * If direction is CLIENT it represents the server channel of the client
 *
 * The server has multiple network managers, one for each connected client
 * @see com.wynntils.hades.protocol.interfaces.IHadesServerContainer
 *
 * In order to create a NetworkManager use
 * @see com.wynntils.hades.protocol.builders.HadesNetworkBuilder
 */
public class HadesNetworkManager extends SimpleChannelInboundHandler<HadesPacket<?>> {

    PacketDirection direction;
    Channel channel;
    IHadesConnection packetListener;

    public HadesNetworkManager(PacketDirection direction, IHadesConnection packetListener) {
        this.direction = direction;
        this.packetListener = packetListener;
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
            channel.eventLoop().execute(() -> {
                channel.writeAndFlush(packet);
            });
            return;
        }

        channel.writeAndFlush(packet);
    }

    /**
     * Closes the provided channel
     *
     * @param reason the reason why the channel was closed
     */
    public void disconnect(String reason) {
        if (packetListener == null || channel == null || !channel.isOpen()) return;

        packetListener.onDisconnect(reason);
        channel.close().awaitUninterruptibly();
    }

    /**
     * Should be called in order to receive and update the packet queue
     */
    public void flushPackets() {
        if (channel == null || !channel.isOpen()) return;

        channel.flush();
    }

    /**
     * @return if the channel is open
     */
    public boolean isOpen() {
        return channel != null && channel.isOpen();
    }

    /**
     * Receives the channel when it's ready to be used
     *
     * @param ctx the context
     * @throws Exception if failed
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.channel = ctx.channel();
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

        ((HadesPacket<IHadesConnection>) msg).process(packetListener); // process the packet
    }

}
