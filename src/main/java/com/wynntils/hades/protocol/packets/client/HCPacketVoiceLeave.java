package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * The player turned voice off; the server drops the session.
 */
public class HCPacketVoiceLeave implements HadesPacket<IHadesServerAdapter> {

    public HCPacketVoiceLeave() { }

    @Override
    public void readData(HadesBuffer buffer) { }

    @Override
    public void writeData(HadesBuffer buffer) { }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handleVoiceLeave(this);
    }

}
