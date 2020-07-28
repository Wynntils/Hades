package com.wynntils.hades;

import com.wynntils.hades.core.HadesRegistry;
import com.wynntils.hades.core.netty.HadesInboundAdapter;
import com.wynntils.hades.core.netty.HadesMessageEncoder;
import com.wynntils.hades.packets.PacketAuthenticate;
import io.netty.channel.socket.SocketChannel;

public class Hades {

    private static boolean registered = false;

    private static void setupPackets() {
        if (registered) return;
        registered = true;

        HadesRegistry.registerPacket(PacketAuthenticate.class);
    }

    public static void insertHades(SocketChannel channel) {
        setupPackets();

        channel.pipeline().addLast("encoder", new HadesMessageEncoder());
        channel.pipeline().addLast("executor", new HadesInboundAdapter());
    }

}
