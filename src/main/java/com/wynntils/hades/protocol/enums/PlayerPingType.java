package com.wynntils.hades.protocol.enums;

public enum PlayerPingType {
    FOCUS(16777215),
    NEED_HELP(10217381),
    WAIT_HERE(8246268),
    ATTACK_HERE(16735324),
    LOOK_HERE(16770669),
    GROUP_UP(5209343);

    private final int color;

    PlayerPingType(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
