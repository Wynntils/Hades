package com.wynntils.hades.core.interfaces;

import com.wynntils.hades.core.utils.BufferUtils;
import io.netty.buffer.ByteBuf;

public interface HadesPacket {

    void serialize(ByteBuf output);
    void deserialize(ByteBuf input);

}
