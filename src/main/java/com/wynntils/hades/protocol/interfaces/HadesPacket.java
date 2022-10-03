package com.wynntils.hades.protocol.interfaces;

import io.netty.channel.Channel;

public abstract class HadesPacket<T extends IHadesConnection> implements HadesPacketContract<T> {
        private Channel channel;

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }
}
