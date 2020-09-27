package com.wynntils.hades.protocol.packets;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.client.HPacketAuthenticate;
import com.wynntils.hades.protocol.packets.client.HPacketSocialUpdate;
import com.wynntils.hades.protocol.packets.client.HPacketUpdateStatus;
import com.wynntils.hades.protocol.packets.server.HPacketUpdateMutual;

import java.util.HashMap;

public enum PacketRegistry {

    /**
     * Packets sent by the server.
     */
    SERVER {
        {
            registerPacket(HPacketUpdateMutual.class);
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
        }
    };

    private static final HashMap<Integer, Class<? extends HadesPacket>> registeredPackets = new HashMap<>();
    private static final HashMap<Class<? extends HadesPacket>, Integer> reverseLookup = new HashMap<>();

    protected void registerPacket(Class<? extends HadesPacket<?>> packetClass) {
        if (registeredPackets.containsValue(packetClass))
            throw new IllegalArgumentException("Packet " + packetClass.getSimpleName() + " was registered twice!");

        int id = registeredPackets.size();
        registeredPackets.put(id, packetClass);
        reverseLookup.put(packetClass, id);
    }

    public static int getPacketId(Class<? extends HadesPacket> packet) {
        return reverseLookup.get(packet);
    }

    public static HadesPacket<?> createPacket(int packetId) throws IllegalAccessException, InstantiationException {
        if (!registeredPackets.containsKey(packetId)) return null;

        return registeredPackets.get(packetId).newInstance();
    }

}
