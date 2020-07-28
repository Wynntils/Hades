package com.wynntils.hades.core.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class BufferUtils {

    public static void writeString(ByteBuf buf, String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public static String readString(ByteBuf buf) {
        int length = buf.readInt();
        return (String) buf.readCharSequence(length, StandardCharsets.UTF_8);
    }

}
