package com.wynntils.hades.protocol.enums;

import com.wynntils.hades.protocol.packets.PacketRegistry;

public enum PacketDirection {

    CLIENT (PacketRegistry.CLIENT, PacketRegistry.SERVER),
    SERVER (PacketRegistry.SERVER, PacketRegistry.CLIENT);

    PacketRegistry encodeRegistry;
    PacketRegistry decodeRegistry;

    PacketDirection(PacketRegistry encodeRegistry, PacketRegistry decodeRegistry) {
        this.encodeRegistry = encodeRegistry;
        this.decodeRegistry = decodeRegistry;
    }

    public PacketRegistry getEncodeRegistry() {
        return encodeRegistry;
    }

    public PacketRegistry getDecodeRegistry() {
        return decodeRegistry;
    }

}
