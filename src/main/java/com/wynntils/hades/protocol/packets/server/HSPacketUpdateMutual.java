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
    float x, y, z;
    int health, mana;
    int maxHealth, maxMana;
    RelationType dominantRelation;
    boolean isPartyMember;
    boolean isMutualFriend;
    boolean isGuildMember;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, String name, float x, float y, float z, int health, int maxHealth, int mana, int maxMana, RelationType dominantRelation, boolean isPartyMember, boolean isMutualFriend, boolean isGuildMember) {
        this.user = user;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.dominantRelation = dominantRelation;
        this.isPartyMember = isPartyMember;
        this.isMutualFriend = isMutualFriend;
        this.isGuildMember = isGuildMember;
    }

    public UUID getUser() {
        return user;
    }

    public String getName() {
        return name;
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

    public RelationType getDominantRelation() {
        return dominantRelation;
    }

    public boolean isPartyMember() {
        return isPartyMember;
    }

    public boolean isMutualFriend() {
        return isMutualFriend;
    }

    public boolean isGuildMember() {
        return isGuildMember;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        user = buffer.readUUID();
        name = buffer.readString();
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        health = buffer.readInt();
        maxHealth = buffer.readInt();
        mana = buffer.readInt();
        maxMana = buffer.readInt();
        dominantRelation = buffer.readEnum(RelationType.class);
        isPartyMember = buffer.readBoolean();
        isMutualFriend = buffer.readBoolean();
        isGuildMember = buffer.readBoolean();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeUUID(user);
        buffer.writeString(name);
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeInt(health);
        buffer.writeInt(maxHealth);
        buffer.writeInt(mana);
        buffer.writeInt(maxMana);
        buffer.writeEnum(dominantRelation);
        buffer.writeBoolean(isPartyMember);
        buffer.writeBoolean(isMutualFriend);
        buffer.writeBoolean(isGuildMember);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }
}
