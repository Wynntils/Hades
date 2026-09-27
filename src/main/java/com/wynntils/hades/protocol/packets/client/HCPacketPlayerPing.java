package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.PlayerPingType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HCPacketPlayerPing implements HadesPacket<IHadesServerAdapter> {

    float x, y, z;
    PlayerPingType pingType;
    String pingTarget;

    public HCPacketPlayerPing() { }

    public HCPacketPlayerPing(float x, float y, float z, PlayerPingType pingType, String pingTarget) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pingType = pingType;
        this.pingTarget = pingTarget;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public PlayerPingType getPingType() {
        return pingType;
    }

    public String getPingTarget() {
        return pingTarget;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        pingType = buffer.readEnum(PlayerPingType.class);
        pingTarget = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeEnum(pingType);
        buffer.writeString(pingTarget);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handlePlayerPing(this);
    }
}
