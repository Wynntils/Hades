package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.PlayerPingType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HCPacketPlayerPing implements HadesPacket<IHadesServerAdapter> {

    float x, y, z;
    PlayerPingType pingType;

    public HCPacketPlayerPing() { }

    public HCPacketPlayerPing(float x, float y, float z, PlayerPingType pingType) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pingType = pingType;
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

    @Override
    public void readData(HadesBuffer buffer) {
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        pingType = buffer.readEnum(PlayerPingType.class);
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeEnum(pingType);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handlePlayerPing(this);
    }
}
