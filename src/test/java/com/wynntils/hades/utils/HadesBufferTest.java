package com.wynntils.hades.utils;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HadesBufferTest {

    private static HadesBuffer newBuffer() {
        HadesBuffer buffer = new HadesBuffer();
        buffer.setBuffer(Unpooled.buffer());
        return buffer;
    }

    @Test
    void byteArrayRoundTrip() {
        HadesBuffer buffer = newBuffer();
        byte[] input = {1, 2, 3, (byte) 0xFF};

        buffer.writeByteArray(input);

        assertEquals(5, buffer.readableBytes()); // 1 varint length byte + 4
        assertArrayEquals(input, buffer.readByteArray(16));
        assertEquals(0, buffer.readableBytes());
    }

    @Test
    void emptyByteArrayRoundTrip() {
        HadesBuffer buffer = newBuffer();
        buffer.writeByteArray(new byte[0]);
        assertArrayEquals(new byte[0], buffer.readByteArray(16));
    }

    @Test
    void readByteArrayRejectsOversizedLength() {
        HadesBuffer buffer = newBuffer();
        buffer.writeByteArray(new byte[17]);
        assertThrows(DecoderException.class, () -> buffer.readByteArray(16));
    }
}
