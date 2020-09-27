package com.wynntils.hades.protocol.interfaces.adapters;

import com.wynntils.hades.protocol.interfaces.IHadesConnection;
import com.wynntils.hades.protocol.packets.client.HPacketAuthenticate;
import com.wynntils.hades.protocol.packets.client.HPacketSocialUpdate;
import com.wynntils.hades.protocol.packets.client.HPacketUpdateStatus;

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
     * Ued to update the player current location and statuses
     * @see HPacketUpdateStatus
     *
     * @param packet the packet itself
     */
    void handleUpdateStatus(HPacketUpdateStatus packet);

}
