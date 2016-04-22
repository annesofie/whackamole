package com.whackamole.game.controller;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.Match;
import com.whackamole.game.screens.CreateGameScreen;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class CreateGameController {

    private CreateGame createGame;
    private Socket socket;
    private String gameName;
    private String nickName;
    private Match match;
    private Preferences prefs;
    private ScreenController screenController;
    private boolean joinGameClicked;
    private boolean createGameClicked;

    public CreateGameController(CreateGame createGame, ScreenController screenController) {
        this.createGame = createGame;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.screenController = screenController;
    }


    public void loadController() {
        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();
        if(!socket.connected()) {
            socket.connect();
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    System.out.println("connected to socket");
                    System.out.println("Socket id: " + socket.id());
                }
            });
        }

        socket.on("new game success", onNewGameSuccess);
        socket.on("game name length", onGameNameLength);
        socket.on("game already exists", onGameAlreadyExists);
        socket.on("connect_error", connectError);
        socket.on("disconnect", disconnected);
        socket.on("join game success", onJoinGameSuccess);
        socket.on("game is full", onGameIsFull);
        socket.on("game nonexistent", onGameNonExistent);
        socket.on("nickname taken", onNickNameTaken);
        socket.on("player left", onPlayerLeft);
    }


    // TODO: grundigere sjekk av lovlige tegn
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

        if(!createGameClicked) {
            createGameClicked = true;
            emitNewGame(gameName, nickName);
        }
    }


    public void joinGame(String gamename, String nickname) {
        this.gameName = gamename;
        this.nickName = nickname;
        if(!joinGameClicked) {
            joinGameClicked = true;
            emitJoinGame(gameName, nickName);
        }
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


    private Emitter.Listener connectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            joinGameClicked = false;
            createGame.setUnableToConnect(true);
        }
    };

    private Emitter.Listener disconnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO: display that user was disconnected by the server
            System.out.println("Disconnected in createGameController.");
            joinGameClicked = false;
        }
    };

    private Emitter.Listener onGameNameLength = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            // This check is already done by the client.
            joinGameClicked = false;
            String msg = (String) args[0];
            System.out.println(msg);
        }
    };

    private Emitter.Listener onNewGameSuccess = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            match = Match.getCurrentMatch();

            String msg = (String) args[0];
            System.out.println(msg);

            createGame.setGameNameAlreadyExists(false);
            createGame.setUnableToConnect(false);

            match.setNickNameOnThisPlayer(nickName);
            match.setGameName(gameName);

            screenController.goToReadyScreen();
            createGameClicked = false;
        }
    };

    private Emitter.Listener onGameIsFull = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
            joinGameClicked = false;
            createGame.setGameIsFull(true);
        }
    };

    private Emitter.Listener onJoinGameSuccess = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            // Start a new match here. This is done in SettingsScreen when creating a game.
            Match.startNewMatch();
            match = Match.getCurrentMatch();

            JsonValue json = new JsonReader().parse((String) args[0]);
            int themeId = json.getInt("themeId");
            int numOfPlayers = json.getInt("numOfPlayers");
            prefs.putInteger(Prefs.NUMOFPLAYERS.key(), numOfPlayers);
            if(!(prefs.getInteger(Prefs.THEME.key()) == themeId)) {
                prefs.putInteger(Prefs.THEME.key(), themeId);
            }
            prefs.flush();

            createGame.setGameIsFull(false);
            createGame.setNoGameWithNameExists(false);
            createGame.setNickNameTaken(false);
            createGame.setUnableToConnect(false);

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
            screenController.goToReadyScreen();
            joinGameClicked = false;
        }
    };

    private Emitter.Listener onGameNonExistent = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            joinGameClicked = false;
            createGame.setNoGameWithNameExists(true);
        }
    };

    private Emitter.Listener onGameAlreadyExists = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            createGameClicked = false;
            createGame.setGameNameAlreadyExists(true);
        }
    };

    private Emitter.Listener onNickNameTaken = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Nickname was taken");
            joinGameClicked = false;
            createGame.setNickNameTaken(true);
        }
    };

    private Emitter.Listener onPlayerLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                JSONObject obj = (JSONObject) args[0];
                System.out.println(obj.getString("nickName") + " left the game.");
                match.removePlayer(obj.getString("nickName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    public void leftGame() {
        if(!createGame.leftGame()) {
            createGame.setLeftGame(true);
            socket.emit("left game", "");

        }
    }


}
