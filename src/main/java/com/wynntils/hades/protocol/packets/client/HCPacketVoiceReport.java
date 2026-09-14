package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Reports a player the sender could hear within the last two minutes.
 */
public class HCPacketVoiceReport implements HadesPacket<IHadesServerAdapter> {

    String targetName;
    String reason;

    public HCPacketVoiceReport() { }

    public HCPacketVoiceReport(String targetName, String reason) {
        this.targetName = targetName;
        this.reason = reason;
    }

    public String getTargetName() {
        return targetName;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        targetName = buffer.readString();
        reason = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(targetName);
        buffer.writeString(reason);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleVoiceReport(this);
    }

}
