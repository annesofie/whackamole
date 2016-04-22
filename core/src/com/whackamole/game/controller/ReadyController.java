package com.whackamole.game.controller;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Player;
import com.whackamole.game.screens.ReadyScreen;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lars on 15/04/16.
 */
public class ReadyController {


    private Socket socket;
    private ReadyScreen readyScreen;
    private boolean isReadyClicked;
    private boolean leftGame;

    public ReadyController(ReadyScreen screen) {
        this.readyScreen = screen;
        this.isReadyClicked = false;
        this.leftGame = false;
    }

    public void loadController() {
        listenToSocket();
    }


    private void listenToSocket() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on("connect_error", connectionError);
        socket.on("player joined", playerJoined);
        socket.on("start game success", startGame);
        socket.on("player ready", playerReady);
        socket.on("player left", onPlayerLeft);

    }

    private Emitter.Listener playerJoined = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Match match = Match.getCurrentMatch();
            try {
                JSONObject obj = (JSONObject) args[0];
                System.out.println(obj.getString("nickName") + " joined the game");
                match.addPlayer(obj.getString("nickName"));
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
}
