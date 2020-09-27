package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.utils.HadesBuffer;

public interface HadesPacket<T extends IHadesConnection> {

    /**
     * Called before process, used to retrieve the packet bytes into the packet pojo.
     * @param buffer the input buffer
     */
    void readData(HadesBuffer buffer);

    /**
     * Called before sending, converts the packet pojo into bytes.
     * @param buffer the output buffer
     */
    void writeData(HadesBuffer buffer);

    /**
     * Call the handler of this packet
     * @see IHadesConnection
     *
     * @param handler the packet handler;
     */
    void process(T handler);

}
