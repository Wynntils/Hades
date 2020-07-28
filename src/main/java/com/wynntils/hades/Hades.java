package com.wynntils.hades;

import com.wynntils.hades.core.HadesRegistry;
import com.wynntils.hades.core.netty.HadesInboundAdapter;
import com.wynntils.hades.core.netty.HadesMessageEncoder;
import com.wynntils.hades.packets.PacketAuthenticate;
import io.netty.channel.socket.SocketChannel;

public class Hades {

    public static void registerPackets() {
        HadesRegistry.registerPacket(PacketAuthenticate.class);
    }

    public static void insertHades(SocketChannel channel) {
        channel.pipeline().addLast("encoder", new HadesMessageEncoder());
        channel.pipeline().addLast("executor", new HadesInboundAdapter());
    }

}
