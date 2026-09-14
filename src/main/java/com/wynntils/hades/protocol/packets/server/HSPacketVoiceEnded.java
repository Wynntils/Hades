package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.VoiceEndReason;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * The server refused a voice join or ended an active voice session.
 */
public class HSPacketVoiceEnded implements HadesPacket<IHadesClientAdapter> {

    VoiceEndReason reason;
    String message;

    public HSPacketVoiceEnded() { }

    public HSPacketVoiceEnded(VoiceEndReason reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    public VoiceEndReason getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        reason = buffer.readEnum(VoiceEndReason.class);
        message = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeEnum(reason);
        buffer.writeString(message);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleVoiceEnded(this);
    }

}
