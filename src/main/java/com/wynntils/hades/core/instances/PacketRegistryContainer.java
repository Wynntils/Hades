package com.wynntils.hades.core.instances;

import com.wynntils.hades.core.interfaces.HadesPacket;
import com.wynntils.hades.core.interfaces.HadesPacketExecutor;
import com.wynntils.hades.core.interfaces.annotations.PacketInfo;

public class PacketRegistryContainer {

    PacketInfo info;
    String classPath;
    HadesPacketExecutor<HadesPacket> executor;

    public PacketRegistryContainer(PacketInfo info, String classPath) {
        this.info = info;
        this.classPath = classPath;
    }

    public void setExecutor(HadesPacketExecutor<HadesPacket> executor) {
        this.executor = executor;
    }

    public PacketInfo getInfo() {
        return info;
    }

    public HadesPacketExecutor<HadesPacket> getExecutor() {
        return executor;
    }

    public Class<? extends HadesPacket> getAsClass() throws Exception {
        return (Class<? extends HadesPacket>) Class.forName(classPath);
    }

}
