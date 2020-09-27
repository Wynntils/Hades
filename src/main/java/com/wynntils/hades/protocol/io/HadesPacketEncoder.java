package com.wynntils.hades.protocol.io;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.PacketRegistry;
import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Converts packet pojo into bytes for messaging.
 *
 * Format:
 * - packetId
 * - message
 */
public class HadesPacketEncoder extends MessageToByteEncoder<HadesPacket<?>> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HadesPacket<?> msg, ByteBuf out) {
        HadesBuffer buffer = new HadesBuffer(out);
        int packetId = PacketRegistry.getPacketId(msg.getClass());

        buffer.writeVarInt(packetId);
        msg.writeData(buffer);
    }

}
