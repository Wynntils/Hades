package com.wynntils.hades.protocol.enums;

/**
 * How far a player opens their voice. Cumulative; audio flows between two players only if
 * each player's tier admits the other.
 */
public enum VoiceTier {
    PARTY,
    FRIENDS_AND_GUILD,
    EVERYONE
}
