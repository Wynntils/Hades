package com.wynntils.hades.protocol.interfaces;

public interface IHadesConnection {

    /**
     * Called BEFORE the socket channel is closed.
     * @param reason the reason for the upcoming disconnection.
     */
    void onDisconnect(String reason);

}
