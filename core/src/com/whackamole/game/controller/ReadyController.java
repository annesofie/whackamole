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


    Socket socket;
    Match match;
    ReadyScreen readyScreen;

    public ReadyController(Match match, ReadyScreen screen) {
        this.match = match;
        this.readyScreen = screen;
    }

    public void loadController() {
        listenToSocket();
    }


    private void listenToSocket() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on("player joined", playerJoined);
        socket.on("start game success", startGame);
        socket.on("player ready", playerReady);

    }


    private Emitter.Listener playerJoined = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                JSONObject obj = (JSONObject) args[0];
                System.out.println(obj.getString("nickName") + " joined the game");
                match.addPlayer(obj.getString("nickName"));
                for(String name: match.getCurrentNickNames()) {
                    System.out.println(name);
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
            JSONObject obj = (JSONObject) args[0];
            System.out.println(obj.toString());
            try {
                if(!obj.getString("nickName").equals(null)){
                    match.setPlayerReady(obj.getString("nickName"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void isReady() {
        JSONObject obj = new JSONObject();
        try {

            System.out.println("Game name in isReady(): " + match.getGameName());
            System.out.println("Nick name in isReady(): " + match.getThisPlayerNickName());

            obj.put("gameName", match.getGameName());
            obj.put("nickName", match.getThisPlayerNickName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Got to isReady()");
        socket.emit("ready", obj);
        match.setThisPlayerReady();
    }


}
