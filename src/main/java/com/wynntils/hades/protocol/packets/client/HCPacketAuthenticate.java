package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Used for authentication.
 * The client sends their Athena token forwards the server which decides how to handle it.
 */
public class HCPacketAuthenticate implements HadesPacket<IHadesServerAdapter> {

    String token;
    String version = null;

    public HCPacketAuthenticate() { }

    public HCPacketAuthenticate(String token, String version) {
        this.token = token;
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        token = buffer.readString();
        // if there is more data, read it
        // this is for backwards compatibility
        if (buffer.readableBytes() > 0) {
            version = buffer.readString();
        }
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(token);
        buffer.writeString(version);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleAuthentication(this);
    }

}
