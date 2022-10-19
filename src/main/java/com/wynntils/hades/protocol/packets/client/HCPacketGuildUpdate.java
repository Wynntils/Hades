package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handles updates to the user's guild
 */
public class HCPacketGuildUpdate implements HadesPacket<IHadesServerAdapter> {

    String guild;

    public HCPacketGuildUpdate() { }

    public HCPacketGuildUpdate(String world) {
        this.guild = world;
    }

    public String getGuild() {
        return guild;
    }


    @Override
    public void readData(HadesBuffer buffer) {
        guild = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeString(guild);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleGuildUpdate(this);
    }

}
