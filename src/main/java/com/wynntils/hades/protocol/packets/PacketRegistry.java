package com.wynntils.hades.protocol.packets;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.client.*;
import com.wynntils.hades.protocol.packets.server.HPacketClearMutual;
import com.wynntils.hades.protocol.packets.server.HPacketDiscordLobbyServer;
import com.wynntils.hades.protocol.packets.server.HPacketUpdateMutual;

import java.util.HashMap;

public enum PacketRegistry {

    /**
     * Packets sent by the server.
     */
    SERVER {
        {
            registerPacket(HPacketUpdateMutual.class);
            registerPacket(HPacketDiscordLobbyServer.class);
            registerPacket(HPacketClearMutual.class);
        }
    },

    /**
     * Packets sent by the client.
     */
    CLIENT {
        {
            registerPacket(HPacketAuthenticate.class);
            registerPacket(HPacketSocialUpdate.class);
            registerPacket(HPacketUpdateStatus.class);
            registerPacket(HPacketDiscordLobbyClient.class);
            registerPacket(HPacketUpdateWorld.class);
        }
    };

    private final HashMap<Integer, Class<? extends HadesPacket>> registeredPackets = new HashMap<>();
    private final HashMap<Class<? extends HadesPacket>, Integer> reverseLookup = new HashMap<>();

    protected void registerPacket(Class<? extends HadesPacket<?>> packetClass) {
        if (registeredPackets.containsValue(packetClass))
            throw new IllegalArgumentException("Packet " + packetClass.getSimpleName() + " was registered twice!");

        int id = registeredPackets.size();
        registeredPackets.put(id, packetClass);
        reverseLookup.put(packetClass, id);
    }

    public int getPacketId(Class<? extends HadesPacket> packet) {
        return reverseLookup.get(packet);
    }

    public HadesPacket<?> createPacket(int packetId) throws IllegalAccessException, InstantiationException {
        if (!registeredPackets.containsKey(packetId)) return null;

        return registeredPackets.get(packetId).newInstance();
    }

}
