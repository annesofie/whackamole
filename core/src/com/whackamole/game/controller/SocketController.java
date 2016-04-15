package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 08.04.2016.
 */

import com.badlogic.gdx.utils.JsonReader;
import com.whackamole.game.model.Board;
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
    private BoardController boardController;
    private String gamename;

    public SocketController(String gamename) {
        this.gamename = gamename;
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
    }


    public void loadController() {

        String message = "";
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gamename);
            json.put("nickName", "anneri");
            message = json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(message);

        socket.emit("new game", message);
        socket.on("new game success", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(args + "start game");
                socket.emit("start game");
            }

        });

        socket.on("new game error", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                System.out.println(args[0] + " failed");
            }
        });
        socket.on("chat message", onNewMessage);
        socket.on("new mole", newMole);


    }

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connected error");
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String message = (String) args[0];
            String[] coord = message.split(",");
            if (coord.length < 2){
                return;
            }
            currentMolePosition = Integer.parseInt(coord[0]);
            currentImgPos = Integer.parseInt(coord[1]);
            System.out.println(currentMolePosition);
            System.out.println(currentImgPos);
        }
    };

    private Emitter.Listener newMole = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            System.out.println(data + " = args from server");

            try {
                System.out.println("inside try");
                currentMolePosition = data.getInt("pos");
                currentImgPos = data.getInt("pic");
                boardController.receiveSocket(currentMolePosition, currentImgPos);
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

    public void setBoardController(BoardController bc){
        this.boardController = bc;
    }

}
