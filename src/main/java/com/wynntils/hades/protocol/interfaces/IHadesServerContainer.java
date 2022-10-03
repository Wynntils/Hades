package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.manager.HadesNetworkManager;

public interface IHadesServerContainer {

    /**
     * Called when the server is closed.
     */
    void close();

    /**
     * Sends a packet forward all connected users.
     * (sends a message to all open channels)
     *
     * @param packet the packet instance.
     */
    void sendPacket(HadesPacket<?> packet);

    /**
     * Updates all available connections to receive their packets.
     * @see HadesNetworkManager#flushPackets()
     */
    void flushPackets();

}
