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
    String name;
    double x, y, z;
    double health, mana;
    double maxHealth, maxMana;
    String world;
    boolean isMutualFriend;
    boolean isMutualPartyMember;
    boolean isMutualGuildMember;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, String name, double x, double y, double z, double health, double mana, double maxHealth, double maxMana, String world, boolean isMutualFriend, boolean isMutualPartyMember, boolean isMutualGuildMember) {
        this.user = user;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.mana = mana;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.world = world;
        this.isMutualFriend = isMutualFriend;
        this.isMutualPartyMember = isMutualPartyMember;
        this.isMutualGuildMember = isMutualGuildMember;
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

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getMana() {
        return mana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public String getWorld() {
        return world;
    }

    public boolean isMutualFriend() {
        return isMutualFriend;
    }

    public boolean isMutualGuildMember() {
        return isMutualGuildMember;
    }

    public boolean isMutualPartyMember() {
        return isMutualPartyMember;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
        name = buffer.readString();
        x = buffer.readDouble();
        y = buffer.readDouble();
        z = buffer.readDouble();
        health = buffer.readDouble();
        maxHealth = buffer.readDouble();
        mana = buffer.readDouble();
        maxMana = buffer.readDouble();
        world = buffer.readString();
        isMutualFriend = buffer.readBoolean();
        isMutualPartyMember = buffer.readBoolean();
        isMutualGuildMember = buffer.readBoolean();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
        buffer.writeString(name);
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeDouble(health);
        buffer.writeDouble(maxHealth);
        buffer.writeDouble(mana);
        buffer.writeDouble(maxMana);
        buffer.writeString(world);
        buffer.writeBoolean(isMutualFriend);
        buffer.writeBoolean(isMutualPartyMember);
        buffer.writeBoolean(isMutualGuildMember);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }

}
