package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * The voice secret the client should hand to Simple Voice Chat, plus where to connect.
 */
public class HSPacketVoiceSecret implements HadesPacket<IHadesClientAdapter> {

    public static final int SECRET_SIZE = 16;

    byte[] secret;
    String host;
    int port;
    double range;
    int keepAliveMs;

    public HSPacketVoiceSecret() { }

    public HSPacketVoiceSecret(byte[] secret, String host, int port, double range, int keepAliveMs) {
        this.secret = secret;
        this.host = host;
        this.port = port;
        this.range = range;
        this.keepAliveMs = keepAliveMs;
    }

    public byte[] getSecret() {
        return secret;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public double getRange() {
        return range;
    }

    public int getKeepAliveMs() {
        return keepAliveMs;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        secret = buffer.readByteArray(SECRET_SIZE);
        host = buffer.readString();
        port = buffer.readVarInt();
        range = buffer.readDouble();
        keepAliveMs = buffer.readVarInt();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        if (secret.length != SECRET_SIZE) {
            throw new IllegalArgumentException("Voice secret must be " + SECRET_SIZE + " bytes");
        }
        buffer.writeByteArray(secret);
        buffer.writeString(host);
        buffer.writeVarInt(port);
        buffer.writeDouble(range);
        buffer.writeVarInt(keepAliveMs);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleVoiceSecret(this);
    }

}
