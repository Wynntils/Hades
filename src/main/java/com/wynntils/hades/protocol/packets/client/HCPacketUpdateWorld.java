package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handles updates to the user world and class
 */
public class HCPacketUpdateWorld implements HadesPacket<IHadesServerAdapter> {

    String world;
    int classId;

    public HCPacketUpdateWorld() { }

    public HCPacketUpdateWorld(String world, int classId) {
        this.world = world;
        this.classId = classId;
    }

    public String getWorld() {
        return world;
    }

    public int getClassId() {
        return classId;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        world = buffer.readString();
        classId = buffer.readVarInt();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(world);
        buffer.writeVarInt(classId);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleUpdateWorld(this);
    }

}
