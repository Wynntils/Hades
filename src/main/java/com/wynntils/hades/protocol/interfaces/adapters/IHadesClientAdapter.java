package com.wynntils.hades.protocol.interfaces.adapters;

import com.wynntils.hades.protocol.interfaces.IHadesAdapter;
import com.wynntils.hades.protocol.packets.server.*;

/**
 * Handles all packets sent by the server
 */
public interface IHadesClientAdapter extends IHadesAdapter {

    /**
     * Handles mutual friends/party information
     *
     * @param packet the packet itself
     */
    void handleUpdateMutual(HSPacketUpdateMutual packet);

    /**
     * Handles sending information required to join a discord lobby
     *
     * @param packet the packet itself
     */
    void handleDiscordLobbyServer(HSPacketDiscordLobbyServer packet);

    /**
     * Handles mutual friend/party cache garbage collection
     *
     * @param packet the packet itself
     */
    void handleClearMutual(HSPacketClearMutual packet);

    /**
     * Handles the server disconnection
     *
     * @param packet the packet itself
     */
    void handleDisconnect(HSPacketDisconnect packet);

    /**
     * Handles the server authentication response
     *
     * @param packet the packet itself
     */
    void handleAuthenticationResponse(HSPacketAuthenticationResponse packet);

    /**
     * Used to indicate that the client is still connected
     *
     * @param packet the packet itself
     */
    void handlePing(HSPacketPong packet);
}
