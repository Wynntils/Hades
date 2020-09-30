package com.wynntils.hades.protocol.interfaces.adapters;

import com.wynntils.hades.protocol.interfaces.IHadesConnection;
import com.wynntils.hades.protocol.packets.server.HPacketClearMutual;
import com.wynntils.hades.protocol.packets.server.HPacketDiscordLobbyServer;
import com.wynntils.hades.protocol.packets.server.HPacketUpdateMutual;

/**
 * Handles all packets sent by the server
 */
public interface IHadesClientAdapter extends IHadesConnection {

    /**
     * Handles mutual friends/party information
     *
     * @param packet the packet itself
     */
    void handleUpdateMutual(HPacketUpdateMutual packet);

    /**
     * Handles sending information required to join a discord lobby
     * 
     * @param packet the packet itself
     */
    void handleDiscordLobbyServer(HPacketDiscordLobbyServer packet);

    /**
     * Handles mutual friend/party cache garbage collection
     *
     * @param packet the packet itself
     */
    void handleClearMutual(HPacketClearMutual packet);

}
