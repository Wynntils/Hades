package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handle client status updates.
 * Used for updating the user coordinates, life and mana.
 */
public class HPacketUpdateStatus implements HadesPacket<IHadesServerAdapter> {

    double x, y, z;
    int life, mana;

    public HPacketUpdateStatus() { }

    public HPacketUpdateStatus(double x, double y, double z, int life, int mana) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.life = life;
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

    public int getLife() {
        return life;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        life = buffer.readVarInt();
        mana = buffer.readVarInt();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeVarInt(life);
        buffer.writeVarInt(mana);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateStatus(this);
    }

}
