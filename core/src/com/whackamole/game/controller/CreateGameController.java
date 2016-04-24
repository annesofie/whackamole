package com.whackamole.game.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.Match;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;


public class CreateGameController implements Disposable {

    private CreateGame createGame;
    private Socket socket;
    private Match match;
    private Preferences prefs;
    private ScreenController screenController;
    private boolean joinGameClicked;
    private boolean createGameClicked;
    private String nickName;

    public CreateGameController(CreateGame createGame, ScreenController screenController) {
        this.createGame = createGame;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.screenController = screenController;
    }


    public void loadController() {
        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        // Attempting connect to game server.
        if(!socket.connected()) {
            socket.connect();
        }

        socket.on(Socket.EVENT_CONNECT, onConnect);
        socket.on(Socket.EVENT_RECONNECT, onReconnect);
        socket.on(Socket.EVENT_RECONNECT_ATTEMPT, onReconnectAttempt);
        socket.on(Socket.EVENT_CONNECT_ERROR, connectError);
        socket.on(Socket.EVENT_ERROR, onSocketError);
        socket.on(Socket.EVENT_DISCONNECT, disconnected);
        socket.on("new game success", onNewGameSuccess);
        socket.on("invalid game name error", onGameNameLength);
        socket.on("game already exists error", onGameAlreadyExists);
        socket.on("join game success", onJoinGameSuccess);
        socket.on("game is full error", onGameIsFull);
        socket.on("game nonexistent error", onGameNonExistent);
        socket.on("nickname taken error", onNickNameTaken);
        socket.on("invalid nickname error", onInvalidNickNameError);
    }


    // TODO: grundigere sjekk av lovlige tegn
    public boolean isValidGameName(String gameName) {
        if(gameName.trim().length() == 0 || gameName.length() < 3 || gameName.length() > 8 || !gameName.matches("^[a-zA-Z0-9_-]+")) {
            System.out.println("Invalid game name");
            createGame.setInvalidGameName(true);
            return false;
        }
        else {
            createGame.setInvalidGameName(false);
            return true;
        }
    }

    public boolean isValidNickName(String nickName) {
        if(nickName.trim().length() == 0 || nickName.length() < 3 || nickName.length() > 8 || !nickName.matches("^[a-zA-Z0-9_-]+")) {
            System.out.println("Invalid nickname");
            createGame.setInvalidNickName(true);
            return false;

        }
        else {
            createGame.setInvalidNickName(false);
            return true;
        }

    }

    public void createGame(String gameName, String nickName) {
        if(!createGameClicked) {
            createGameClicked = true;
            emitNewGame(gameName, nickName);
        }
    }


    public void joinGame(String gameName, String nickName) {
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
        System.out.println("Requesting a new game.");
        socket.emit("new game", json);

    }

    private void emitJoinGame(String gameName, String nickName) {
        this.nickName = nickName;
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("join game", json);
        System.out.println("Requesting to join a game.");
    }

    private Emitter.Listener onReconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            joinGameClicked = false;
            System.out.println("Reconnected to server.");
            createGame.setUnableToConnect(false);
        }
    };

    private Emitter.Listener onReconnectAttempt = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Attempting to reconnect.");
        }
    };


    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connected to socket.");
            System.out.println("Socket id: " + socket.id());
            createGame.setUnableToConnect(false);
        }
    };

    private Emitter.Listener onSocketError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            joinGameClicked = false;
            System.out.println("Socket error. Attempting to reconnect");
        }
    };

    private Emitter.Listener connectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connection error. Unable to connect.");
            joinGameClicked = false;
            createGame.setUnableToConnect(true);
        }
    };

    private Emitter.Listener disconnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO: display that user was disconnected by the server
            System.out.println("Disconnected...");
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

    private Emitter.Listener onInvalidNickNameError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            createGame.setInvalidNickName(true);
        }
    };

    private Emitter.Listener onNewGameSuccess = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            match = Match.getCurrentMatch();

            JsonValue json = new JsonReader().parse((String) args[0]);
            int numOfPlayers = json.getInt("numOfPlayers");
            String nickName = json.getString("nickname");
            String gameName = json.getString("name");

            createGame.setGameNameAlreadyExists(false);
            createGame.setUnableToConnect(false);
            createGame.setInvalidNickName(false);
            createGame.setInvalidGameName(false);

            match.setNickNameOnThisPlayer(nickName);
            match.setGameName(gameName);
            match.setNumOfPlayers(numOfPlayers);

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
            match = Match.getCurrentMatch();

            JsonValue json = new JsonReader().parse((String) args[0]);
            int themeId = json.getInt("themeId");
            int numOfPlayers = json.getInt("numOfPlayers");
            String gameName = json.getString("name");

            // Sets the correct theme based on the creators choice of theme for this game.
            prefs.putInteger(Prefs.NUMOFPLAYERS.key(), numOfPlayers);
            if(!(prefs.getInteger(Prefs.THEME.key()) == themeId)) {
                prefs.putInteger(Prefs.THEME.key(), themeId);
            }
            prefs.flush();

            createGame.setGameIsFull(false);
            createGame.setNoGameWithNameExists(false);
            createGame.setNickNameTaken(false);
            createGame.setUnableToConnect(false);
            createGame.setInvalidNickName(false);
            createGame.setInvalidGameName(false);

            match.setNumOfPlayers(numOfPlayers);
            match.setNickNameOnThisPlayer(nickName);
            match.setGameName(gameName);

            JSONObject obj = new JSONObject();
            try {
                obj.put("gameName", gameName);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Updates the list of attenders on currently on the server
            JsonValue attendersJson = json.get("attenders");
            for(JsonValue attender : attendersJson.iterator()) {
                String nickname = attender.getString("nickName");
                System.out.println("In 'join game success', nickname from server: " + nickname);
                if(!nickname.equals(match.getThisPlayerNickName())) {
                    match.addPlayer(nickname);
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

    @Override
    public void dispose() {
        socket.off(Socket.EVENT_RECONNECT_ATTEMPT, onReconnectAttempt);
        socket.off(Socket.EVENT_CONNECT, onConnect);
        socket.off(Socket.EVENT_RECONNECT, onReconnect);
        socket.off(Socket.EVENT_CONNECT_ERROR, connectError);
        socket.off(Socket.EVENT_ERROR, onSocketError);
        socket.off(Socket.EVENT_DISCONNECT, disconnected);
        socket.off("new game success", onNewGameSuccess);
        socket.off("invalid game name error", onGameNameLength);
        socket.off("game already exists error", onGameAlreadyExists);
        socket.off("join game success", onJoinGameSuccess);
        socket.off("game is full error", onGameIsFull);
        socket.off("game nonexistent error", onGameNonExistent);
        socket.off("nickname taken error", onNickNameTaken);
        socket.off("invalid nickname error", onInvalidNickNameError);

    }
}
