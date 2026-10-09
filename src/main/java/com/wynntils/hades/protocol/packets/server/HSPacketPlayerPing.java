package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.Direction;
import com.wynntils.hades.protocol.enums.PlayerPingType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HSPacketPlayerPing implements HadesPacket<IHadesClientAdapter> {
    String username;
    float x, y, z;
    Direction direction;
    PlayerPingType pingType;
    String pingTarget;

    public HSPacketPlayerPing() { }

    public HSPacketPlayerPing(String username, float x, float y, float z, Direction direction, PlayerPingType pingType, String pingTarget) {
        this.username = username;
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = direction;
        this.pingType = pingType;
        this.pingTarget = pingTarget;
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

    public Direction getDirection() {
        return direction;
    }

    public PlayerPingType getPingType() {
        return pingType;
    }

    public String getPingTarget() {
        return pingTarget;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        username = buffer.readString();
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        direction = buffer.readEnum(Direction.class);
        pingType = buffer.readEnum(PlayerPingType.class);
        pingTarget = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(username);
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeEnum(direction);
        buffer.writeEnum(pingType);
        buffer.writeString(pingTarget);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handlePlayerPing(this);
    }
}
