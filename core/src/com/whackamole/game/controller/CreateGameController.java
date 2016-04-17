package com.whackamole.game.controller;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.Match;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

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

    public CreateGameController(CreateGame createGame, Match match) {
        this.createGame = createGame;
        this.match = match;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

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

        Gdx.app.log("debug", "Got to createGame()");

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
                newGame(gameName, nickName);
                System.out.println("Socket id: " + socket.id());
            }
        });

        socket.on("new game success", onNewGameSuccess);
        socket.on("new game error", onNewGameError);
    }

    private void newGame(String gameName, String nickName) {
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
            json.put("numOfPlayers", prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("new game", json);
    }


    private Emitter.Listener onNewGameError = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
            createGame.setGameNameAlreadyExists(true);
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
        }
    };


}
