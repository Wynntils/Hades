package com.wynntils.hades.protocol.enums;

public enum HadesVersion {
    UNKNOWN,
    VERSION_0_6_1, // Gear sharing support with the introduction of versioning
    // 0.6.2 was only library updates, fully compatible with 0.6.1
    VERSION_0_6_3  // Gear updates decoupled from position/vitals via HCPacketGearUpdate
}
