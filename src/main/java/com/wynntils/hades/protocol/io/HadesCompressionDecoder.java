package com.wynntils.hades.protocol.io;

import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Decompress all sent packets that are over the threshold using ZLib.
 * @see Inflater
 *
 * Format:
 * - compressionLength
 * - compressedMessage
 */
public class HadesCompressionDecoder extends ByteToMessageDecoder {

    private final Inflater inflater = new Inflater();
    private final int threshold;

    public HadesCompressionDecoder(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) return;

        HadesBuffer buffer = new HadesBuffer(in);
        int compressionLength = buffer.readVarInt();

        if (compressionLength == 0) {
            out.add(buffer.readBytes(buffer.readableBytes()));
            return;
        }

        if (compressionLength < threshold) {
            throw new DecoderException("Invalid compression threshold, size " + compressionLength + " is below " + threshold);
        }

        if (compressionLength > 2097152) {
            throw new DecoderException("Invalid compression threshold, size " + compressionLength + " is over the limit of " + 2097152);
        }

        byte[] toDecompress = new byte[in.readableBytes()];
        buffer.readBytes(toDecompress);
        inflater.setInput(toDecompress);

        byte[] output = new byte[compressionLength];
        inflater.inflate(output);
        inflater.reset();

        out.add(Unpooled.wrappedBuffer(output));
    }

}
