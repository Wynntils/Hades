package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.objects.VoicePeer;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Full replacement of the set of players this client can currently hear or talk to.
 */
public class HSPacketVoicePeers implements HadesPacket<IHadesClientAdapter> {

    List<VoicePeer> peers;

    public HSPacketVoicePeers() { }

    public HSPacketVoicePeers(List<VoicePeer> peers) {
        this.peers = peers;
    }

    public List<VoicePeer> getPeers() {
        return peers;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        int size = buffer.readVarInt();
        peers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            peers.add(new VoicePeer(buffer.readUUID(), buffer.readString(), buffer.readBoolean(), buffer.readBoolean()));
        }
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeVarInt(peers.size());
        for (VoicePeer peer : peers) {
            buffer.writeUUID(peer.getUuid());
            buffer.writeString(peer.getName());
            buffer.writeBoolean(peer.isDisabled());
            buffer.writeBoolean(peer.isPartyMember());
        }
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleVoicePeers(this);
    }

}
