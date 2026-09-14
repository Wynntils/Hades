package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.VoiceTier;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Asks the server for a voice secret. Sent when voice is enabled, after every Wynncraft
 * world switch, and after the server ends a session with TIMED_OUT.
 */
public class HCPacketVoiceJoin implements HadesPacket<IHadesServerAdapter> {

    int svcCompatVersion;
    VoiceTier tier;
    String instance;

    public HCPacketVoiceJoin() { }

    public HCPacketVoiceJoin(int svcCompatVersion, VoiceTier tier, String instance) {
        this.svcCompatVersion = svcCompatVersion;
        this.tier = tier;
        this.instance = instance;
    }

    public int getSvcCompatVersion() {
        return svcCompatVersion;
    }

    public VoiceTier getTier() {
        return tier;
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        svcCompatVersion = buffer.readVarInt();
        tier = buffer.readEnum(VoiceTier.class);
        instance = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeVarInt(svcCompatVersion);
        buffer.writeEnum(tier);
        buffer.writeString(instance);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleVoiceJoin(this);
    }

}
