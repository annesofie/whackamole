package com.whackamole.game.utils;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;


public class SocketRetreiver {


    private Socket mSocket = null;
    private static SocketRetreiver instance;


    private  SocketRetreiver() {
        try {
            mSocket = IO.socket(Constants.SERVER_URL);
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
