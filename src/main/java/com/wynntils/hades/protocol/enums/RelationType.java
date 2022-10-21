package com.wynntils.hades.protocol.enums;

// Enum order represents importance, the earlier the value is, the more "significant" it is
// (when a mutual user is both a friend and a guild mate, friendship relation type is more important)
public enum RelationType {
    PARTY,
    FRIEND,
    GUILD,
    NONE
}
