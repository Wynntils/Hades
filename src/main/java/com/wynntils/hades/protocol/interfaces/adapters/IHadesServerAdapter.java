package com.wynntils.hades.protocol.interfaces.adapters;

import com.wynntils.hades.protocol.interfaces.IHadesAdapter;
import com.wynntils.hades.protocol.packets.client.*;

/**
 * Handles all packets sent by the client
 */
public interface IHadesServerAdapter extends IHadesAdapter {

    /**
     * Used to authenticate the user via Athena.
     * Disconnects the user if the token is invalid.
     * @see HCPacketAuthenticate
     *
     * @param packet the packet itself
     */
    default void handleAuthentication(HCPacketAuthenticate packet) {};

    /**
     * Used to update the player's social conditions.
     * @see HCPacketSocialUpdate
     *
     * @param packet the packet itself
     */
    default void handleSocialUpdate(HCPacketSocialUpdate packet) {};

    /**
     * Used to update the player's current location and statuses
     * 
     * @see HCPacketUpdateStatus
     *
     * @param packet the packet itself
     */
    default void handleUpdateStatus(HCPacketUpdateStatus packet) {};

    /**
     * Used to update the player's current world and class
     * @see HCPacketUpdateWorld
     *
     * @param packet the packet itself
     */
    default void handleUpdateWorld(HCPacketUpdateWorld packet) {};

    /**
     * Used to send the information required to join a discord lobby to the player
     * joining
     * 
     * @see HCPacketDiscordLobbyClient
     * 
     * @param packet the packet itself
     */
    default void handleDiscordLobbyServer(HCPacketDiscordLobbyClient packet) {};

    /**
     * Used to update the player's current guild
     *
     * @param packet the packet itself
     */
    default void handleUpdateGuild(HCPacketUpdateGuild packet) {};
}
