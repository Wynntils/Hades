package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handle client status updates.
 * Used for updating the user coordinates, life and mana.
 */
public class HCPacketUpdateStatus implements HadesPacket<IHadesServerAdapter> {

    double x, y, z;
    double health, mana;

    public HCPacketUpdateStatus() { }

    public HCPacketUpdateStatus(double x, double y, double z, double health, double mana) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.mana = mana;
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
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readDouble();
        mana = buffer.readDouble();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeDouble(health);
        buffer.writeDouble(mana);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateStatus(this);
    }

}
