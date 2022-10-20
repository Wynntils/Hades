package com.wynntils.hades.protocol.packets.server;

import com.wynntils.hades.protocol.enums.AuthenticationResponse;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesClientAdapter;
import com.wynntils.hades.utils.HadesBuffer;

/**
 * Tells the client that the authentication was successful.
 */
public class HSPacketAuthenticationResponse implements HadesPacket<IHadesClientAdapter> {

    AuthenticationResponse response;
    String message;

    public HSPacketAuthenticationResponse() {
    }

    public HSPacketAuthenticationResponse(AuthenticationResponse response, String message) {
        this.response = response;
        this.message = message;
    }

    public AuthenticationResponse getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void readData(HadesBuffer buffer) {
        response = buffer.readEnum(AuthenticationResponse.class);
        message = buffer.readString();
    }

    @Override
    public void writeData(HadesBuffer buffer) {
        buffer.writeEnum(response);
        buffer.writeString(message);
    }

    @Override
    public void process(IHadesClientAdapter handler) {
        handler.handleAuthenticationResponse(this);
    }

}
