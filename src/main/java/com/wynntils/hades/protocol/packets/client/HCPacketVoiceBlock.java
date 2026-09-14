package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Blocks (or unblocks) another player for voice in both directions.
 */
public class HCPacketVoiceBlock implements HadesPacket<IHadesServerAdapter> {

    String targetName;
    boolean blocked;

    public HCPacketVoiceBlock() { }

    public HCPacketVoiceBlock(String targetName, boolean blocked) {
        this.targetName = targetName;
        this.blocked = blocked;
    }

    public String getTargetName() {
        return targetName;
    }

    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        targetName = buffer.readString();
        blocked = buffer.readBoolean();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(targetName);
        buffer.writeBoolean(blocked);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleVoiceBlock(this);
    }

}
