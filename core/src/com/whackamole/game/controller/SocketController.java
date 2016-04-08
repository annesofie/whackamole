package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 08.04.2016.
 */

import com.badlogic.gdx.utils.JsonReader;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketController {

    private SocketRetreiver soRetreiver;
    Socket socket;
    JsonReader reader;
    private int currentMolePosition;

    public SocketController() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
            }

        });

        socket.on("new mole", newMole);
    }

    private Emitter.Listener newMole = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String currentMole = (String) args[0];
            String[] coord = currentMole.split(",");
            if (coord.length < 2){
                return;
            }
            currentMolePosition = Integer.parseInt(coord[0]);
        }
    };

    public int getMolePosition() {
        return currentMolePosition;
    };

}
