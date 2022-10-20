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
    double maxHealth, maxMana;

    public HCPacketUpdateStatus() { }

    public HCPacketUpdateStatus(double x, double y, double z, double health, double maxHealth, double mana, double maxMana) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
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

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readDouble();
        maxHealth = buffer.readDouble();
        mana = buffer.readDouble();
        maxMana = buffer.readDouble();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeDouble(health);
        buffer.writeDouble(maxHealth);
        buffer.writeDouble(mana);
        buffer.writeDouble(maxMana);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateStatus(this);
    }

}
