package com.wynntils.hades.core.netty;

import com.wynntils.hades.core.HadesRegistry;
import com.wynntils.hades.core.instances.PacketRegistryContainer;
import com.wynntils.hades.core.interfaces.HadesPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HadesInboundAdapter extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof ByteBuf)) return;
        ByteBuf buf = (ByteBuf)msg;

        int packetId = buf.readInt();
        PacketRegistryContainer container = HadesRegistry.getPacket(packetId);

        // calls deserialization
        HadesPacket packet = container.getAsClass().newInstance();
        packet.deserialize(buf);

        container.getExecutor().execute(packet, ctx);

        buf.release();
    }

}
