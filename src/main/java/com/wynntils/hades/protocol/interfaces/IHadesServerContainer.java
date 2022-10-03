package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.objects.HadesConnection;

public interface IHadesServerContainer {

    /**
     * Called when an upcoming connection is detected.
     * This is used to register the client inside some sort of collection.
     *
     * Recommendation: store temporarily inside a Set until the player authenticates, after that move to a map.
     * Reason: Set and maps are faster for interactions along with that the authentication process is easier.
     *
     * @see HadesHandlerFactory
     *
     * @param manager represents the network manager created by the factory.
     */
    void registerClient(HadesConnection manager);

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
     * @see HadesConnection#flushPackets()
     */
    void flushPackets();

}
