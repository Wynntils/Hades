package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Tells the client that the server is disconnecting the user.
 */
public class HSPacketDisconnect implements HadesPacket<IHadesClientAdapter> {

    String reason;

    public HSPacketDisconnect() { }

    public HSPacketDisconnect(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        reason = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(reason);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleDisconnect(this);
    }

}
