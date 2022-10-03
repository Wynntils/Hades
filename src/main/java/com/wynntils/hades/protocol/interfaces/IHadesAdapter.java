package com.wynntils.hades.protocol.interfaces;

public interface IHadesAdapter {

    /**
     * Called BEFORE the socket channel is closed.
     * @param reason the reason for the upcoming disconnection.
     */
    void onDisconnect(String reason);

}
