package com.wynntils.hades.protocol.io;

import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class HadesIntSplitter extends ByteToMessageDecoder {

    private final HadesBuffer buffer = new HadesBuffer();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        in.markReaderIndex();

        byte[] byteBuffer = new byte[3];
        for (int i = 0; i < byteBuffer.length; i++) {
            if (!in.isReadable()) {
                in.resetReaderIndex();
                return;
            }

            byteBuffer[i] = in.readByte();
            if (byteBuffer[i] < 0) continue;

            buffer.setBuffer(in);
            try {
                int j = buffer.readVarInt();

                if (in.readableBytes() >= j) {
                    out.add(in.readBytes(j));
                    return;
                }

                in.resetReaderIndex();
            } finally {
                buffer.release();
            }

            return;
        }

        throw new CorruptedFrameException("int length is wider than 21-bit!");
    }

}
