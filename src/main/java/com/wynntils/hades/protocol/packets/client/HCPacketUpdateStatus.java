package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handle client status updates.
 * Used for updating the user coordinates, life and mana.
 */
public class HCPacketUpdateStatus implements HadesPacket<IHadesServerAdapter> {

    float x, y, z;
    int health, mana;
    int maxHealth, maxMana;
    String helmet, chestplate, leggings, boots, ringOne, ringTwo, bracelet, necklace, heldItem;

    public HCPacketUpdateStatus() { }

    public HCPacketUpdateStatus(float x, float y, float z, int health, int maxHealth, int mana, int maxMana) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
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

    public HCPacketUpdateStatus(float x, float y, float z, int health, int maxHealth, int mana, int maxMana, String helmet, String chestplate, String leggings, String boots, String ringOne, String ringTwo, String bracelet, String necklace, String heldItem) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
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

    public int getMana() {
        return mana;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
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
        x = buffer.readFloat();
        y = buffer.readFloat();
        z = buffer.readFloat();
        health = buffer.readInt();
        maxHealth = buffer.readInt();
        mana = buffer.readInt();
        maxMana = buffer.readInt();

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
        buffer.writeFloat(x);
        buffer.writeFloat(y);
        buffer.writeFloat(z);
        buffer.writeInt(health);
        buffer.writeInt(maxHealth);
        buffer.writeInt(mana);
        buffer.writeInt(maxMana);

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
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateStatus(this);
    }

}
