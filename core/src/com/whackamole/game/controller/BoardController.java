package com.whackamole.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import com.whackamole.game.views.Assets;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;


public class BoardController {


    private int touch_x, touch_y, counter = 4;
    private Board board;
    private Sound hitsound;
    private Sound speech;
    private Socket socket;
    private String gameName;
    private String nickName;
    private Mole mole;
    private Match match;
    private Preferences prefs;
    private ScreenController screenController;

    public BoardController(Board board, ScreenController screenController) {

        this.board = board;
        this.match = Match.getCurrentMatch();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.screenController = screenController;

    }

    public void loadController() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();
        String themePath = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())).path();

        this.gameName = match.getGameName();
        this.nickName = match.getThisPlayerNickName();

        this.hitsound = Gdx.audio.newSound(Gdx.files.internal(Assets.HITSOUND));
        this.speech = Gdx.audio.newSound(Gdx.files.internal(themePath + Assets.SPEECH));

        SocketRetreiver socketRetreiver = SocketRetreiver.getInstance();
        socket = socketRetreiver.getSocket();

        socket.on("start game error", startGameError);
        socket.on("player hit", playerHit);
        socket.on("new mole", onNewMole);
        socket.on("game finished", onGameFinished);
    }


    private Emitter.Listener onGameFinished = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JsonValue attendersJson = new JsonReader().parse((String) args[0]);

            // Update the scoreboard just to make sure all scores were correct and updated
            for(JsonValue attender : attendersJson.iterator()) {
                match.setScoreToUser(attender.getString("nickName"), attender.getInt("points"));
            }

            // TODO: Switch to GameOverScreen when that one is done
            screenController.goToGameOverScreen();
            socket.disconnect();
        }
    };

    private  Emitter.Listener startGameError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            //TODO: HÅNDTERE FEIL VED START GAME. GI BRUKER FEEDBACK OG GÅ TIL GAME OVER SCREEN

            String msg = (String) args[0];
            System.out.println(msg);
        }
    };


    private Emitter.Listener playerHit = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject obj = (JSONObject) args[0];
            try {
                String nickName = obj.getString("nickName");
                int points = obj.getInt("points");
                int totalScore = obj.getInt("totalScore");
                if(match.getThisPlayerNickName().equals(nickName)) {
                    System.out.print("You hit the last mole for " + points + " points!");
                    board.setHitTheLastMole(true, points);
                    match.setScoreToUser(nickName, totalScore);
                }
                else {
                    try {
                        board.getCurrentMole().finish();
                    }catch(Exception e) {}
                    board.setHitTheLastMole(false, 0);
                    match.setScoreToUser(nickName, totalScore);
                }
                board.setNotFirstRound();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    // NEW MOLES FROM SERVER ARRIVES HERE
    private Emitter.Listener onNewMole = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            try {
                board.getCurrentMole().finish();
            }catch(Exception e) {}
            JSONObject obj = (JSONObject) args[0];
            try {
                receiveSocket(obj.getInt("pos"), obj.getInt("pic"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch_x = screenX;
        touch_y = screenY;
        mole = board.getCurrentMole();
        if(mole != null && mole.getBoundingRectangle().contains(touch_x, touch_y)){
            if(this.prefs.getBoolean(Prefs.ISSOUND.key())) {
                hitsound.play(1);
            }
            JSONObject json = new JSONObject();
            try {
                json.put("gameName", gameName);
                json.put("nickName", nickName);
                json.put("mole", mole.getMoleImageId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socket.emit("mole hit", json);
        }
        return true;
    }

    public void receiveSocket(int mole, int img){
        board.setMole(mole, img);
        if(this.prefs.getBoolean(Prefs.ISSOUND.key()) && img == 0){
            if(counter == 4){
                speech.play();
                counter = 0;
            } else counter++;
        }
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}
    public boolean keyDown(int keycode) {return false;}

    public boolean keyUp(int keycode) {return false;}

    public boolean keyTyped(char character) {return false;}

    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
    public boolean mouseMoved(int screenX, int screenY) {return false;}

    public boolean scrolled(int amount) {return false;}
}
