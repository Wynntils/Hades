package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.UUID;

/**
 * Used for updating mutual friends/party users their status.
 */
public class HSPacketUpdateMutual implements HadesPacket<IHadesClientAdapter> {

    UUID user;
    double x, y, z;
    int health, mana;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, double x, double y, double z, int health, int mana) {
        this.user = user;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.mana = mana;
    }

    public UUID getUser() {
        return user;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readVarInt();
        mana = buffer.readVarInt();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeVarInt(health);
        buffer.writeVarInt(mana);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }

}
