package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.manager.HadesNetworkManager;

public interface IHadesConnection {

    /**
     * Called BEFORE the socket channel is closed.
     * @param reason the reason for the upcoming disconnection.
     */
    void onDisconnect(String reason);

    /**
     * Called when an upcoming connection is detected.
     * This is used to register the client inside some sort of collection.
     *
     * Recommendation: store temporarily inside a Set until the player authenticates, after that move to a map.
     * Reason: Set and maps are faster for interactions along with that the authentication process is easier.
     *
     * @param client represents the network manager created by the factory.
     */
    void onConnect(HadesNetworkManager client);

}
