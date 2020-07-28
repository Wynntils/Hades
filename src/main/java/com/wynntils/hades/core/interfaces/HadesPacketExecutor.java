package com.wynntils.hades.core.interfaces;

import io.netty.channel.ChannelHandlerContext;

public interface HadesPacketExecutor<T extends HadesPacket> {

    void execute(T packet, ChannelHandlerContext context);

}
