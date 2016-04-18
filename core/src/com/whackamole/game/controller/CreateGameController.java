package com.whackamole.game.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Player;
import com.whackamole.game.model.Theme;
import com.whackamole.game.screens.CreateGameScreen;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lars on 13/04/16.
 */
public class CreateGameController {

    private CreateGame createGame;
    private Socket socket;
    private String gameName;
    private String nickName;
    private Match match;
    private Preferences prefs;
    private CreateGameScreen createGameScreen;

    public CreateGameController(CreateGame createGame, Match match, CreateGameScreen createGameScreen) {
        this.createGame = createGame;
        this.match = match;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.createGameScreen = createGameScreen;
    }

    public boolean isValidGameName(String gameName) {
        if(gameName.trim().length() == 0 || gameName.length() < 3) {
            createGame.setInvalidGameName(true);
            return false;

        }
        else {
            createGame.setInvalidGameName(false);
            return true;
        }
    }

    public boolean isValidNickName(String nickName) {
        if(nickName.trim().length() == 0 || nickName.length() < 3) {
            createGame.setInvalidNickName(true);
            return false;

        }
        else {
            createGame.setInvalidNickName(false);
            return true;
        }

    }

    public void createGame(String gamename, String nickname) {
        this.gameName = gamename;
        this.nickName = nickname;

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
                emitNewGame(gameName, nickName);
                System.out.println("Socket id: " + socket.id());
            }
        });

        socket.on("new game success", onNewGameSuccess);
        socket.on("game name length error", onGameNameLengthError);
        socket.on("game already exists error", onGameAlreadyExists);
    }


    public void joinGame(String gamename, String nickname) {
        this.gameName = gamename;
        this.nickName = nickname;

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
                emitJoinGame(gameName, nickName);
                System.out.println("Socket id: " + socket.id());
            }
        });

        socket.on("join game success", onJoinGameSuccess);
        socket.on("game is full error", onGameIsFullError);
        socket.on("game nonexistent error", onGameNonExistentError);
    }

    private void emitNewGame(String gameName, String nickName) {
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
            json.put("numOfPlayers", prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
            json.put("themeId", prefs.getInteger(Prefs.THEME.key()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("new game", json);
    }

    private void emitJoinGame(String gameName, String nickName) {
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("join game", json);
    }


    private Emitter.Listener onGameNameLengthError = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
        }
    };

    private Emitter.Listener onNewGameSuccess = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
            createGame.setGameNameAlreadyExists(false);
            match.setNickNameOnThisPlayer(nickName);
            match.setGameName(gameName);
            createGameScreen.goToReadyScreen();
        }
    };

    private Emitter.Listener onGameIsFullError = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
            createGame.setGameIsFull(true);
        }
    };

    private Emitter.Listener onJoinGameSuccess = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            JsonValue json = new JsonReader().parse((String) args[0]);
            int themeId = json.getInt("themeId");
            if(!(prefs.getInteger(Prefs.THEME.key()) == themeId)) {
                prefs.putInteger(Prefs.THEME.key(), themeId);
                prefs.flush();
                createGameScreen.reloadBoardRenderer();
                createGameScreen.reloadReadyRenderer();
            }

            createGame.setNoGameWithNameExists(false);
            match.setNickNameOnThisPlayer(nickName);
            match.setGameName(gameName);


            JSONObject obj = new JSONObject();
            try {
                obj.put("gameName", gameName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            List<String> nickNames = new ArrayList<String>();
            JsonValue attendersJson = json.get("attenders");
            for(JsonValue attender : attendersJson.iterator()) {
                String nickName = attender.getString("nickName");
                nickNames.add(nickName);
            }
            for(String nickName : nickNames) {
                if(!nickName.equals(match.getThisPlayerNickName())) {
                    match.addPlayer(nickName);
                }
            }

            System.out.print("You were registered in the game " + gameName + " server as: " + nickName);
            createGameScreen.goToReadyScreen();
        }
    };

    private Emitter.Listener onGameNonExistentError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            // VARSLE BRUKER AT SPILLET IKKE FINNES PÅ SKJERMEN
            // VARSLE BRUKER AT SPILLET IKKE FINNES PÅ SKJERMEN
            createGame.setNoGameWithNameExists(true);
        }
    };

    private Emitter.Listener onGameAlreadyExists = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            createGame.setGameNameAlreadyExists(true);
            // VARLSE BRUKEREN AT SPILLET FINNES FRA FØR PÅ SKJERMEN
        }
    };


}
