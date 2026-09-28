package com.wynntils.hades.protocol.enums;

public enum PlayerPingType {
    FOCUS(0xFFFFFFFF),
    NEED_HELP(0xFF9BE7A5),
    WAIT_HERE(0xFF7DD3FC),
    ATTACK_HERE(0xFFFF5C5C),
    LOOK_HERE(0xFFFFE66D),
    GROUP_UP(0xFF4F7CFF);

    private final int color;

    PlayerPingType(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
