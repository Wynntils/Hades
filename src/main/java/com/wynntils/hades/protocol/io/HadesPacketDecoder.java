package com.wynntils.hades.protocol.io;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.PacketRegistry;
import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.List;

/**
 * Converts the packet bytes into their respective POJOs
 *
 * Format:
 * - packetId
 * - message
 */
public class HadesPacketDecoder extends ByteToMessageDecoder {

    private final PacketRegistry registry;
    private final HadesBuffer buffer = new HadesBuffer();

    public HadesPacketDecoder(PacketRegistry registry) {
        this.registry = registry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) return;

        buffer.setBuffer(in);

        int packetId = buffer.readVarInt();
        HadesPacket<?> packet = registry.createPacket(packetId);

        if (packet == null) {
            throw new IOException("Invalid packet id " + packetId);
        }

        packet.readData(buffer);
        out.add(packet);
    }

}
