package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.PlayerPingType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HSPacketPlayerPing implements HadesPacket<IHadesClientAdapter> {
    String username;
    float x, y, z;
    PlayerPingType pingType;

    public HSPacketPlayerPing() { }

    public HSPacketPlayerPing(String username, float x, float y, float z, PlayerPingType pingType) {
        this.username = username;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pingType = pingType;
    }

    public String getUsername() {
        return username;
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
        username = buffer.readString();
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        pingType = buffer.readEnum(PlayerPingType.class);
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(username);
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeEnum(pingType);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handlePlayerPing(this);
    }
}
