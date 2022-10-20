package com.wynntils.hades.protocol.interfaces;

public interface IHadesAdapter {

    /**
     * Called when the connection is connected
     */
    default void onConnect() {};

    /**
     * Called when the connection is disconnected
     */
    default void onDisconnect() {};
}
