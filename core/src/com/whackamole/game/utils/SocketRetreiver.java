package com.whackamole.game.utils;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketRetreiver {


    private Socket mSocket;

    public SocketRetreiver() {

        try {
            mSocket = IO.socket(Constants.SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        mSocket.connect();
    }

    public Socket getSocket() {
        return mSocket;
    }
}
