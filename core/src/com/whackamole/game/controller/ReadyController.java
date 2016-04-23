package com.whackamole.game.controller;

import com.badlogic.gdx.utils.Disposable;
import com.whackamole.game.model.Match;
import com.whackamole.game.screens.ReadyScreen;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadyController implements Disposable {


    private Socket socket;
    private ReadyScreen readyScreen;
    private boolean isReadyClicked;

    public ReadyController(ReadyScreen screen) {
        this.readyScreen = screen;
        this.isReadyClicked = false;
    }

    public void loadController() {
        listenToSocket();
    }


    private void listenToSocket() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on(Socket.EVENT_CONNECT, onConnect);
        socket.on(Socket.EVENT_CONNECT_ERROR, connectionError);
        socket.on(Socket.EVENT_RECONNECT, onReconnect);
        socket.on(Socket.EVENT_DISCONNECT, disconnected);
        socket.on(Socket.EVENT_RECONNECT_ATTEMPT, onReconnecting);
        socket.on("player joined", playerJoined);
        socket.on("start game success", startGame);
        socket.on("player ready", playerReady);
        socket.on("player left", onPlayerLeft);

    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connected to socket.");
            System.out.println("Socket id: " + socket.id());
        }
    };

    private Emitter.Listener onReconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            isReadyClicked = false;
            System.out.println("Reconnected.");
        }
    };

    private Emitter.Listener disconnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            isReadyClicked = false;
            System.out.println("Disconnected from server");
        }
    };

    private Emitter.Listener onReconnecting = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            isReadyClicked = false;
            System.out.println("Attempting to reconnect.");
        }
    };

    private Emitter.Listener playerJoined = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Match match = Match.getCurrentMatch();
            try {
                JSONObject obj = (JSONObject) args[0];
                System.out.println(obj.getString("nickName") + " joined the game");
                String nickname = obj.getString("nickName");
                if(!nickname.equals(match.getThisPlayerNickName())) {
                    match.addPlayer(obj.getString("nickName"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener startGame = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            readyScreen.goToGameScreen();
        }
    };

    private Emitter.Listener playerReady = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Match match = Match.getCurrentMatch();
            JSONObject obj = (JSONObject) args[0];
            try {
                if(!obj.getString("nickName").equals(null)){
                    match.setPlayerReady(obj.getString("nickName"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener connectionError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO: Varsle bruker om at det er nettverksproblemer
            isReadyClicked = false;
        }
    };

    public void isReady() {
        Match match = Match.getCurrentMatch();
        if(!isReadyClicked) {
            isReadyClicked = true;
            JSONObject obj = new JSONObject();
            try {
                obj.put("gameName", match.getGameName());
                obj.put("nickName", match.getThisPlayerNickName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socket.emit("ready", obj);
            match.setThisPlayerReady();
        }
    }

    private Emitter.Listener onPlayerLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Match match = Match.getCurrentMatch();
            try {
                JSONObject obj = (JSONObject) args[0];
                System.out.println(obj.getString("nickName") + " left the game.");
                match.removePlayer(obj.getString("nickName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void dispose() {
        socket.off(Socket.EVENT_CONNECT, onConnect);
        socket.off(Socket.EVENT_CONNECT_ERROR, connectionError);
        socket.off(Socket.EVENT_RECONNECT, onReconnect);
        socket.off(Socket.EVENT_DISCONNECT, disconnected);
        socket.off(Socket.EVENT_RECONNECT_ATTEMPT, onReconnecting);
        socket.off("player joined", playerJoined);
        socket.off("start game success", startGame);
        socket.off("player ready", playerReady);
        socket.off("player left", onPlayerLeft);
    }
}
