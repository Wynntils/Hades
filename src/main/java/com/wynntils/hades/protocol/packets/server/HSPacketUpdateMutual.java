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
    double health, mana;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, double x, double y, double z, double health, double mana) {
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

    public double getHealth() {
        return health;
    }

    public double getMana() {
        return mana;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readDouble();
        mana = buffer.readDouble();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeDouble(health);
        buffer.writeDouble(mana);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }

}
