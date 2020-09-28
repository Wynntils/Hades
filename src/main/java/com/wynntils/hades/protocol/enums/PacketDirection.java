package com.wynntils.hades.protocol.enums;

import com.wynntils.hades.protocol.packets.PacketRegistry;

public enum PacketDirection {

    CLIENT (PacketRegistry.SERVER),
    SERVER (PacketRegistry.CLIENT);

    PacketRegistry registry;

    PacketDirection(PacketRegistry registry) {
        this.registry = registry;
    }

    public PacketRegistry getRegistry() {
        return registry;
    }

}
