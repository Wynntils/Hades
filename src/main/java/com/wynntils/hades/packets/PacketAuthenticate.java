package com.wynntils.hades.packets;

import com.wynntils.hades.core.enums.ConnectionSide;
import com.wynntils.hades.core.interfaces.HadesPacket;
import com.wynntils.hades.core.interfaces.annotations.PacketInfo;
import com.wynntils.hades.core.utils.BufferUtils;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

@PacketInfo(id = 0, side = ConnectionSide.CLIENT)
public class PacketAuthenticate implements HadesPacket {

    UUID authToken;

    public PacketAuthenticate(UUID authToken) {
        this.authToken = authToken;
    }

    public UUID getAuthToken() {
        return authToken;
    }

    @Override
    public void serialize(ByteBuf output) {
        BufferUtils.writeString(output, authToken.toString());
    }

    @Override
    public void deserialize(ByteBuf input) {
        authToken = UUID.fromString(BufferUtils.readString(input));
    }

}
