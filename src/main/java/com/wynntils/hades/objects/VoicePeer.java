package com.wynntils.hades.objects;

import java.util.UUID;

/**
 * Another player currently on voice that this client can hear or talk to.
 */
public class VoicePeer {

    private final UUID uuid;
    private final String name;
    private final boolean disabled;
    private final boolean partyMember;

    public VoicePeer(UUID uuid, String name, boolean disabled, boolean partyMember) {
        this.uuid = uuid;
        this.name = name;
        this.disabled = disabled;
        this.partyMember = partyMember;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public boolean isPartyMember() {
        return partyMember;
    }

}
