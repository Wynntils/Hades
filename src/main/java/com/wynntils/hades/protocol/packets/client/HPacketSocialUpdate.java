package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.enums.PacketAction;
import com.wynntils.hades.protocol.enums.SocialType;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

import java.util.List;

/**
 * Handles social updates
 * Supported types: FRIEND; PARTY.
 */
public class HPacketSocialUpdate implements HadesPacket<IHadesServerAdapter> {

    List<String> updatedNames;

    PacketAction action;
    SocialType type;

    public HPacketSocialUpdate(List<String> updatedNames, PacketAction action, SocialType type) {
        this.updatedNames = updatedNames;
        this.action = action;
        this.type = type;
    }

    public List<String> getUpdatedNames() {
        return updatedNames;
    }

    public PacketAction getAction() {
        return action;
    }

    public SocialType getType() {
        return type;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        updatedNames = buffer.readStringList();
        action = PacketAction.valueOf(buffer.readString());
        type = SocialType.valueOf(buffer.readString());
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeStringList(updatedNames);
        buffer.writeString(action.name());
        buffer.writeString(type.name());
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleSocialUpdate(this);
    }

}
