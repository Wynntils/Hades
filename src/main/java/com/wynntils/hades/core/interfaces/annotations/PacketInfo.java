package com.wynntils.hades.core.interfaces.annotations;

import com.wynntils.hades.core.enums.ConnectionSide;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PacketInfo {

    int id();
    ConnectionSide side();

}
