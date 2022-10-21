package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.RelationType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.UUID;

/**
 * Used for updating mutual friends/party users' statuses when they are on the same server as the client.
 */
public class HSPacketUpdateMutual implements HadesPacket<IHadesClientAdapter> {
    UUID user;
    String name;
    double x, y, z;
    int health, mana;
    int maxHealth, maxMana;
    RelationType relationType;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, String name, double x, double y, double z, int health, int maxHealth, int mana, int maxMana, RelationType relationType) {
        this.user = user;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.relationType = relationType;
    }

    public UUID getUser() {
        return user;
    }

    public String getName() {
        return name;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
        name = buffer.readString();
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readInt();
        maxHealth = buffer.readInt();
        mana = buffer.readInt();
        maxMana = buffer.readInt();
        relationType = buffer.readEnum(RelationType.class);
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
        buffer.writeString(name);
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeInt(health);
        buffer.writeInt(maxHealth);
        buffer.writeInt(mana);
        buffer.writeInt(maxMana);
        buffer.writeEnum(relationType);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }
}
