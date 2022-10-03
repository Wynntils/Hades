package com.wynntils.hades.protocol.interfaces;

import io.netty.channel.ChannelId;

public abstract class HadesPacket<T extends IHadesConnection> implements HadesPacketContract<T> {
        private ChannelId channel;

        public ChannelId getChannelId() {
            return channel;
        }

        public void setChannelId(ChannelId channel) {
            this.channel = channel;
        }
}
