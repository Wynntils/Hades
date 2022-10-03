package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.objects.HadesConnection;

public interface HadesHandlerFactory {

    /**
     * Every client connection into the server requires their own server adapter.
     * The purpose of this is to simply create new adapters.
     *
     * @return a new server adapter used for the incoming connection.
     */
    IHadesAdapter createHandler(HadesConnection networkManager);

}
