package com.wynntils.hades.utils;

public class HadesMath {

    public static int getVarIntSize(int input) {
        for (int i = 1; i < 5; i++) {
            if ((input & -1 << i * 7) != 0) continue;

            return i;
        }

        return 5;
    }

}
