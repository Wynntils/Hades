package com.wynntils.hades.protocol.enums;

import com.wynntils.hades.protocol.packets.PacketRegistry;

public enum PacketDirection {

    CLIENT (PacketRegistry.SERVER, PacketRegistry.CLIENT),
    SERVER (PacketRegistry.CLIENT, PacketRegistry.SERVER);

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
