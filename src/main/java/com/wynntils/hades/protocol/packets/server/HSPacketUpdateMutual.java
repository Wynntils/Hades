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
    boolean isPartyMember;
    boolean isMutualFriend;
    boolean isGuildMember;
    String helmet, chestplate, leggings, boots, ringOne, ringTwo, bracelet, necklace, heldItem;

    public HSPacketUpdateMutual() { }

    public HSPacketUpdateMutual(UUID user, String name, float x, float y, float z, int health, int maxHealth, int mana, int maxMana, boolean isPartyMember, boolean isMutualFriend, boolean isGuildMember) {
        this.user = user;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.isPartyMember = isPartyMember;
        this.isMutualFriend = isMutualFriend;
        this.isGuildMember = isGuildMember;
        this.helmet = "";
        this.chestplate = "";
        this.leggings = "";
        this.boots = "";
        this.ringOne = "";
        this.ringTwo = "";
        this.bracelet = "";
        this.necklace = "";
        this.heldItem = "";
    }

    public HSPacketUpdateMutual(UUID user, String name, float x, float y, float z, int health, int maxHealth, int mana, int maxMana, boolean isPartyMember, boolean isMutualFriend, boolean isGuildMember, String helmet, String chestplate, String leggings, String boots, String ringOne, String ringTwo, String bracelet, String necklace, String heldItem) {
        this.user = user;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.isPartyMember = isPartyMember;
        this.isMutualFriend = isMutualFriend;
        this.isGuildMember = isGuildMember;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.ringOne = ringOne;
        this.ringTwo = ringTwo;
        this.bracelet = bracelet;
        this.necklace = necklace;
        this.heldItem = heldItem;
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

    public boolean isPartyMember() {
        return isPartyMember;
    }

    public boolean isMutualFriend() {
        return isMutualFriend;
    }

    public boolean isGuildMember() {
        return isGuildMember;
    }

    public String getHelmet() {
        return helmet;
    }

    public String getChestplate() {
        return chestplate;
    }

    public String getLeggings() {
        return leggings;
    }

    public String getBoots() {
        return boots;
    }

    public String getRingOne() {
        return ringOne;
    }

    public String getRingTwo() {
        return ringTwo;
    }

    public String getBracelet() {
        return bracelet;
    }

    public String getNecklace() {
        return necklace;
    }

    public String getHeldItem() {
        return heldItem;
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
        isPartyMember = buffer.readBoolean();
        isMutualFriend = buffer.readBoolean();
        isGuildMember = buffer.readBoolean();

        if (buffer.readableBytes() > 0) {
            helmet = buffer.readString();
            chestplate = buffer.readString();
            leggings = buffer.readString();
            boots = buffer.readString();
            ringOne = buffer.readString();
            ringTwo = buffer.readString();
            bracelet = buffer.readString();
            necklace = buffer.readString();
            heldItem = buffer.readString();
        } else {
            helmet = "";
            chestplate = "";
            leggings = "";
            boots = "";
            ringOne = "";
            ringTwo = "";
            bracelet = "";
            necklace = "";
            heldItem = "";
        }
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
        buffer.writeBoolean(isPartyMember);
        buffer.writeBoolean(isMutualFriend);
        buffer.writeBoolean(isGuildMember);

        if (helmet.isEmpty() && chestplate.isEmpty() && leggings.isEmpty() && boots.isEmpty() && ringOne.isEmpty() && ringTwo.isEmpty() && bracelet.isEmpty() && necklace.isEmpty() && heldItem.isEmpty()) return;

        buffer.writeString(helmet);
        buffer.writeString(chestplate);
        buffer.writeString(leggings);
        buffer.writeString(boots);
        buffer.writeString(ringOne);
        buffer.writeString(ringTwo);
        buffer.writeString(bracelet);
        buffer.writeString(necklace);
        buffer.writeString(heldItem);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleUpdateMutual(this);
    }
}
