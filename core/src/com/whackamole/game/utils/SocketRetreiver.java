package com.whackamole.game.utils;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketRetreiver {
    /**
     *  Singleton that provides a socket to the application
     */

    private Socket mSocket = null;
    private static SocketRetreiver instance;


    private  SocketRetreiver() {
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            mSocket = IO.socket(Constants.SERVER_URL, opts);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static SocketRetreiver getInstance(){
        if(instance == null){
            instance = new SocketRetreiver();
        }
        return instance;
    }

    public Socket getSocket() {
        return mSocket;
    }
}
