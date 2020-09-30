package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Used for sending information to join a discord lobby to the client joining
 */
public class HPacketDiscordLobbyServer implements HadesPacket<IHadesClientAdapter> {

    long lobbyId = 0L;
    String lobbySecret = null;

    public HPacketDiscordLobbyServer() {
    }

    public HPacketDiscordLobbyServer(long lobbyId, String lobbySecret) {
        this.lobbyId = lobbyId;
        this.lobbySecret = lobbySecret;
    }

    public long getLobbyId() {
        return lobbyId;
    }

    public String getLobbySecret() {
        return lobbySecret;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        lobbyId = buffer.readLong();
        lobbySecret = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeLong(lobbyId);
        buffer.writeString(lobbySecret);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleDiscordLobbyServer(this);
    }

}
