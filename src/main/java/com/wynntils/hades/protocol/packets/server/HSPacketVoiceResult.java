package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.VoiceResultKind;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Outcome of a block/unblock/report request, for display to the player.
 */
public class HSPacketVoiceResult implements HadesPacket<IHadesClientAdapter> {

    VoiceResultKind kind;
    boolean success;
    String message;

    public HSPacketVoiceResult() { }

    public HSPacketVoiceResult(VoiceResultKind kind, boolean success, String message) {
        this.kind = kind;
        this.success = success;
        this.message = message;
    }

    public VoiceResultKind getKind() {
        return kind;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        kind = buffer.readEnum(VoiceResultKind.class);
        success = buffer.readBoolean();
        message = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeEnum(kind);
        buffer.writeBoolean(success);
        buffer.writeString(message);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleVoiceResult(this);
    }

}
