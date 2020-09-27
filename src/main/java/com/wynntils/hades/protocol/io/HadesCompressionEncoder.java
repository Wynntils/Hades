package com.wynntils.hades.protocol.io;

import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.zip.Deflater;

/**
 * Compress all sent packets that are over the threshold using ZLib.
 * @see Deflater
 *
 * Format:
 * - compressionLength
 * - compressedMessage
 */
public class HadesCompressionEncoder extends MessageToByteEncoder<ByteBuf> {

    private final byte[] byteBuffer = new byte[8192];
    private final Deflater deflater = new Deflater();

    private final int threshold;

    public HadesCompressionEncoder(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        int messageSize = msg.readableBytes();
        HadesBuffer buffer = new HadesBuffer(out);

        if (messageSize < threshold) { // below threshold
            buffer.writeVarInt(0);
            buffer.writeBytes(msg);
            return;
        }

        byte[] toCompress = new byte[messageSize];
        msg.readBytes(toCompress);

        buffer.writeVarInt(toCompress.length);

        deflater.setInput(toCompress, 0, messageSize);
        deflater.finish();

        // writing data
        while (!deflater.finished()) {
            int hold = deflater.deflate(byteBuffer);
            buffer.writeBytes(byteBuffer, 0, hold);
        }

        deflater.reset();
    }

}
