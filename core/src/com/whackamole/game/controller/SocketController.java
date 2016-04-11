package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 08.04.2016.
 */

import com.badlogic.gdx.utils.JsonReader;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;
import org.json.JSONException;


public class SocketController {

    private SocketRetreiver soRetreiver;
    Socket socket;
    JsonReader reader;
    private int currentMolePosition, currentImgPos;

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
        String gamename = "anso";
        socket.emit("new game", gamename);
        socket.on("new game success", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(args + "start game");
                socket.emit("start game");
            }

        });
        socket.on("new mole", newMole);
    }

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connected error");
        }
    };

    private Emitter.Listener newMole = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            System.out.println(data);

            try {
                System.out.println("inside try");
                currentMolePosition = data.getInt("pos");
                currentImgPos = data.getInt("pic");
            } catch (JSONException e) {
                System.out.println("nothing");
                return;
            }
            System.out.println(currentMolePosition);
            System.out.println(currentImgPos);
        }
    };

    public int getMolePosition() {
        return currentMolePosition;
    };
    public int getImgPosition() {
        return currentImgPos;
    };

}
