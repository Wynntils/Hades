package com.wynntils.hades.protocol.io;

import com.wynntils.hades.utils.HadesBuffer;
import com.wynntils.hades.utils.HadesMath;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HadesIntPrepender extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out)  {
        int i = msg.readableBytes();
        int j = HadesMath.getVarIntSize(i);

        if (j > 3) throw new IllegalArgumentException("Var int limit exceeded.");

        HadesBuffer buffer = new HadesBuffer(out);
        buffer.ensureWritable(j + i);
        buffer.writeVarInt(i);
        buffer.writeBytes(msg, msg.readerIndex(), i);
    }

}
