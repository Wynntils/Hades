package com.wynntils.hades.core.netty;

import com.wynntils.hades.core.HadesRegistry;
import com.wynntils.hades.core.interfaces.HadesPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;
import java.util.List;

public class HadesMessageEncoder extends MessageToMessageEncoder<HadesPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HadesPacket msg, List<Object> out) throws IOException {
        ByteBuf buffer = ctx.alloc().buffer();

        // writing the packet id
        buffer.writeInt(HadesRegistry.getPacketId(msg.getClass()));

        // call the packet serialization
        msg.serialize(buffer);

        out.add(buffer);
    }

}
