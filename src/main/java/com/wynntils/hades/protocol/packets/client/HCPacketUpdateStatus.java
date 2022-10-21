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
    int health, mana;
    int maxHealth, maxMana;

    public HCPacketUpdateStatus() { }

    public HCPacketUpdateStatus(double x, double y, double z, int health, int maxHealth, int mana, int maxMana) {
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

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readInt();
        maxHealth = buffer.readInt();
        mana = buffer.readInt();
        maxMana = buffer.readInt();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeInt(health);
        buffer.writeInt(maxHealth);
        buffer.writeInt(mana);
        buffer.writeInt(maxMana);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateStatus(this);
    }

}
