package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Sent by the client when gear changes, and once on initial login/world load.
 * Decoupled from HCPacketUpdateStatus so gear is not retransmitted on every position tick.
 */
public class HCPacketGearUpdate implements HadesPacket<IHadesServerAdapter> {

    String helmet, chestplate, leggings, boots, ringOne, ringTwo, bracelet, necklace, heldItem;

    public HCPacketGearUpdate() {
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

    public HCPacketGearUpdate(String helmet, String chestplate, String leggings, String boots,
                              String ringOne, String ringTwo, String bracelet, String necklace,
                              String heldItem) {
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

    public String getHelmet() { return helmet; }
    public String getChestplate() { return chestplate; }
    public String getLeggings() { return leggings; }
    public String getBoots() { return boots; }
    public String getRingOne() { return ringOne; }
    public String getRingTwo() { return ringTwo; }
    public String getBracelet() { return bracelet; }
    public String getNecklace() { return necklace; }
    public String getHeldItem() { return heldItem; }

    @Override
    public void readData(HadesBuffer buffer) {
        helmet = buffer.readString();
        chestplate = buffer.readString();
        leggings = buffer.readString();
        boots = buffer.readString();
        ringOne = buffer.readString();
        ringTwo = buffer.readString();
        bracelet = buffer.readString();
        necklace = buffer.readString();
        heldItem = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
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
        handler.handleGearUpdate(this);
    }
}
