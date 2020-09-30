package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.UUID;

/**
 * Used for sending information to join a discord lobby to the server to send to
 * the client joining
 */
public class HPacketDiscordLobbyClient implements HadesPacket<IHadesServerAdapter> {

    UUID joiningPlayer = null;
    long lobbyId = 0L;
    String lobbySecret = null;

    public HPacketDiscordLobbyClient() { }

    public HPacketDiscordLobbyClient(UUID joiningPlayer, long lobbyId, String lobbySecret) {
        this.joiningPlayer = joiningPlayer;
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
        joiningPlayer = buffer.readUUID();
        lobbyId = buffer.readLong();
        lobbySecret = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(joiningPlayer);
        buffer.writeLong(lobbyId);
        buffer.writeString(lobbySecret);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleDiscordLobbyServer(this);
    }

}
