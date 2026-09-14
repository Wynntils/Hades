package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.VoiceTier;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Updates the audience tier, voice instance (housing plot / raid run / "" for open world)
 * and the SVC "disabled" toggle of an active voice session.
 */
public class HCPacketVoiceUpdate implements HadesPacket<IHadesServerAdapter> {

    VoiceTier tier;
    String instance;
    boolean disabled;

    public HCPacketVoiceUpdate() { }

    public HCPacketVoiceUpdate(VoiceTier tier, String instance, boolean disabled) {
        this.tier = tier;
        this.instance = instance;
        this.disabled = disabled;
    }

    public VoiceTier getTier() {
        return tier;
    }

    public String getInstance() {
        return instance;
    }

    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        tier = buffer.readEnum(VoiceTier.class);
        instance = buffer.readString();
        disabled = buffer.readBoolean();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeEnum(tier);
        buffer.writeString(instance);
        buffer.writeBoolean(disabled);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleVoiceUpdate(this);
    }

}
