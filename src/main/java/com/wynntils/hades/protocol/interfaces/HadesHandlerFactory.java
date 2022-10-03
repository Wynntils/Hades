package com.wynntils.hades.protocol.interfaces;

import com.wynntils.hades.objects.HadesConnection;
import com.wynntils.hades.protocol.interfaces.adapters.IHadesServerAdapter;

public interface HadesHandlerFactory {

    /**
     * Every client connection into the server requires their own server adapter.
     * The purpose of this is to simply create new adapters.
     *
     * @return a new server adapter used for the incoming connection.
     */
    IHadesServerAdapter createHandler(HadesConnection networkManager);

}
