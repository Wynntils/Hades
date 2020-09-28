package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Used for authentication.
 * The client sends their Athena token forwards the server which decides how to handle it.
 */
public class HPacketAuthenticate implements HadesPacket<IHadesServerAdapter> {

    String token;

    public HPacketAuthenticate() { }

    public HPacketAuthenticate(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        token = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(token);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleAuthentication(this);
    }

}
