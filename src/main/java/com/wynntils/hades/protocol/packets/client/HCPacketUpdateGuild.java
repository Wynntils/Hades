package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Handles updates to the user's guild
 */
public class HCPacketUpdateGuild implements HadesPacket<IHadesServerAdapter> {

    String guild;

    public HCPacketUpdateGuild() { }

    public HCPacketUpdateGuild(String guild) {
        this.guild = guild;
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
        handler.handleUpdateGuild(this);
    }

}
