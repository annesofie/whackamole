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
    ReadyScreen screen;

    public ReadyController(Match match, ReadyScreen screen) {
        this.match = match;
        this.screen = screen;
    }

    public void loadController() {
        listenToSocket();
    }


    public void listenToSocket() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on("players", currentPlayers);
        socket.on("start game success", startGame);

    }


    private Emitter.Listener currentPlayers = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                JSONArray arr = new JSONArray(args);
                List<Player> players = new ArrayList<Player>();
                for(int i = 0; i < arr.length(); i++) {
                    JSONObject attender = (JSONObject) arr.get(i);
                    String nickName = attender.getString("nickName");
                    Player player = new Player(nickName);
                    players.add(player);
                }
                match.setPlayerList(players);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener startGame = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            screen.goToGameScreen();
        }
    };

    public void isReady() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("gameName", match.getGameName());
            obj.put("nickName", match.getThisPlayerNickName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Got to isReady()");
        socket.emit("ready", obj);
    }


}
