package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

public class HSPacketPong implements HadesPacket<IHadesClientAdapter> {
    long time;

    public HSPacketPong() {

    }

    public HSPacketPong(long time) {
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
    public void process(IHadesClientAdapter handler) {
        handler.handlePing(this);
    }

    public long getTime() {
        return time;
    }
}
