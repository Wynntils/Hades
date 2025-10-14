package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.HadesVersion;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Used for authentication.
 * The client sends their Athena token forwards the server which decides how to handle it.
 */
public class HCPacketAuthenticate implements HadesPacket<IHadesServerAdapter> {

    String token;
    HadesVersion version;

    public HCPacketAuthenticate() { }

    public HCPacketAuthenticate(String token) {
        this.token = token;
    }

    public HCPacketAuthenticate(String token, HadesVersion version) {
        this.token = token;
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public HadesVersion getVersion() {
        return version;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        token = buffer.readString();

        if (buffer.readableBytes() > 0) {
            version = buffer.readEnum(HadesVersion.class);
        } else {
            version = HadesVersion.UNKNOWN;
        }
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(token);
        buffer.writeEnum(version);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleAuthentication(this);
    }

}
