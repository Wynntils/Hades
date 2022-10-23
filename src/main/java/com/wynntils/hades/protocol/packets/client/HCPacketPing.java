package com.wynntils.hades.protocol.packets.client;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HCPacketPing implements HadesPacket<IHadesServerAdapter> {
    long time;

    public HCPacketPing() {

    }

    public HCPacketPing(long time) {
        this.time = time;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        time = buffer.readLong();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeLong(time);
    }

    @Override
    public void process(IHadesServerAdapter handler) {
        handler.handlePing(this);
    }

    public long getTime() {
        return time;
    }
}
