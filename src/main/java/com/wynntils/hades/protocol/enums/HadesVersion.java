package com.wynntils.hades.protocol.enums;

public enum HadesVersion {
    UNKNOWN,
    VERSION_0_6_1, // Gear sharing support with the introduction of versioning
    VERSION_0_6_2  // Gear updates decoupled from position/vitals via HCPacketGearUpdate
}
