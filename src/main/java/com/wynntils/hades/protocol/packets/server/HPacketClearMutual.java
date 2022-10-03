package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.UUID;

/**
 * Tells the client to clear all registered information about mutual friends/party members
 */
public class HPacketClearMutual extends HadesPacket<IHadesClientAdapter> {

    UUID user;

    public HPacketClearMutual() { }

    public HPacketClearMutual(UUID user) {
        this.user = user;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleClearMutual(this);
    }

}
