package com.wynntils.hades.protocol.interfaces.adapters;

import com.wynntils.hades.protocol.interfaces.IHadesConnection;
import com.wynntils.hades.protocol.packets.client.*;

/**
 * Handles all packets sent by the client
 */
public interface IHadesServerAdapter extends IHadesConnection {

    /**
     * Used to authenticate the user via Athena.
     * Disconnects the user if the token is invalid.
     * @see HPacketAuthenticate
     *
     * @param packet the packet itself
     */
    void handleAuthentication(HPacketAuthenticate packet);

    /**
     * Used to update the player social conditions.
     * @see HPacketSocialUpdate
     *
     * @param packet the packet itself
     */
    void handleSocialUpdate(HPacketSocialUpdate packet);

    /**
     * Used to update the player current location and statuses
     * 
     * @see HPacketUpdateStatus
     *
     * @param packet the packet itself
     */
    void handleUpdateStatus(HPacketUpdateStatus packet);

    /**
     * Used to update the player current world and class
     * @see HPacketUpdateWorld
     *
     * @param packet the packet itself
     */
    void handleUpdateWorld(HPacketUpdateWorld packet);

    /**
     * Used to send the information required to join a discord lobby to the player
     * joining
     * 
     * @see HPacketDiscordLobbyClient
     * 
     * @param packet the packet itself
     */
    void handleDiscordLobbyServer(HPacketDiscordLobbyClient packet);

}
