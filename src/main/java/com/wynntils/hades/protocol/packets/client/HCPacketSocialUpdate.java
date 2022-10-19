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
public class HCPacketSocialUpdate implements HadesPacket<IHadesServerAdapter> {

    List<String> updatedNames;

    PacketAction action;
    SocialType socialType;

    public HCPacketSocialUpdate() { }

    public HCPacketSocialUpdate(List<String> updatedNames, PacketAction action, SocialType socialType) {
        this.updatedNames = updatedNames;
        this.action = action;
        this.socialType = socialType;
    }

    public List<String> getUpdatedNames() {
        return updatedNames;
    }

    public PacketAction getAction() {
        return action;
    }

    public SocialType getSocialType() {
        return socialType;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        updatedNames = buffer.readStringList();
        action = buffer.readEnum(PacketAction.class);
        socialType = buffer.readEnum(SocialType.class);
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeStringList(updatedNames);
        buffer.writeEnum(action);
        buffer.writeEnum(socialType);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleSocialUpdate(this);
    }

}
