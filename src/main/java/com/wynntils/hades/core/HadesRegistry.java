package com.wynntils.hades.core;

import com.wynntils.hades.core.instances.PacketRegistryContainer;
import com.wynntils.hades.core.interfaces.HadesPacket;
import com.wynntils.hades.core.interfaces.HadesPacketExecutor;
import com.wynntils.hades.core.interfaces.annotations.PacketInfo;

import java.util.HashMap;

/**
 * Handles every packet registration and getting
 */
public class HadesRegistry {

    private static final HashMap<String, Integer> PACKET_IDS = new HashMap<>();
    private static final HashMap<Integer, PacketRegistryContainer> REGISTERED_PACKETS = new HashMap<>();

    /**
     * Returns the packet ID based on the Hades Packet class
     * @see HadesPacket
     *
     * @param packetClass the HadesPacket class type
     * @return the Packet ID if registered, otherwise -1
     */
    public static int getPacketId(Class<? extends HadesPacket> packetClass) {
        return PACKET_IDS.getOrDefault(packetClass.getName(), -1);
    }

    /**
     * Returns the PacketRegistryContainer of the provided id
     * @see PacketRegistryContainer
     *
     * @param id the required packet ID
     * @return the container if present otherwise null
     */
    public static PacketRegistryContainer getPacket(int id) {
        return REGISTERED_PACKETS.getOrDefault(id, null);
    }

    /**
     * Returns the PacketRegistryContainer based on the provided HadesPacket class type
     * @see PacketRegistryContainer
     * @see HadesPacket
     *
     * @param packetClass the HadesPacket class type
     * @return the container if present otherwise null
     */
    public static PacketRegistryContainer getPacketByClass(Class<? extends HadesPacket> packetClass) {
        return getPacket(getPacketId(packetClass));
    }

    /**
     * Tries to register a new Packet into the decoder/encoder system
     * If the class doesn't contains the PacketInfo annotation, it will be ignored
     * Use this to register packets at the Hades class
     * @see HadesPacket
     * @see PacketInfo
     *
     * @param packetClass the HadesPacket class type
     * @return true if success
     */
    public static boolean registerPacket(Class<? extends HadesPacket> packetClass) {
        PacketInfo info = packetClass.getAnnotation(PacketInfo.class);
        if (info == null) return true;

        PACKET_IDS.put(packetClass.getName(), info.id());
        REGISTERED_PACKETS.put(info.id(), new PacketRegistryContainer(info, packetClass.getName()));
        return false;
    }

    /**
     * Tries to set the executor for the provided HadesPacket class type.
     * Each packet should have only one executor.
     * If no executors where provided on execution, the packet will be ignored.
     * @see HadesPacket
     * @see HadesPacketExecutor
     *
     * @param packetClass the HadesPacket class type
     * @param executor the provided HadesPacketExecutor
     * @return true if success
     */
    public static <T extends HadesPacket> boolean setPacketExecutor(Class<? extends HadesPacket> packetClass, HadesPacketExecutor<T> executor) {
        PacketRegistryContainer container = getPacketByClass(packetClass);
        if (container == null) return false;

        container.setExecutor(executor);
        return true;
    }

}
