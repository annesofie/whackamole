package com.whackamole.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

public class BoardController {


    private int touch_x, touch_y;
    private Board board;
    private Sound hitsound;
    private Sound speech;
    private Socket socket;
    private String gameName;
    private String nickName;
    private Mole mole;
    private Match match;
    private Preferences prefs;

    public BoardController(Board board, Match match) {

        this.board = board;
        this.match = match;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

    }

    public void loadController() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        this.gameName = match.getGameName();
        this.nickName = match.getThisPlayerNickName();

        this.hitsound = Gdx.audio.newSound(Gdx.files.internal(FileName.HITSOUND.filename()));
        this.speech = Gdx.audio.newSound(Gdx.files.internal(Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())).path() + FileName.SPEECHSOUND.filename()));

        SocketRetreiver socketRetreiver = SocketRetreiver.getInstance();
        socket = socketRetreiver.getSocket();

        socket.on("start game success", startGameSuccess);
        socket.on("start game error", startGameError);

        socket.on("player hit", playerHit);
        socket.on("new mole", onNewMole);
    }

    private Emitter.Listener playerScore = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Player score: " + args);
        }
    };

    private  Emitter.Listener startGameError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
        }
    };

    private Emitter.Listener startGameSuccess = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
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
                System.out.println("Got here, but not to into the if sentence.. :(");
                if(match.getThisPlayerNickName().equals(nickName)) {
                    System.out.print("You hit the last mole for " + points + " points!");
                    board.setHitTheLastMole(true, points);
                    match.setScoreToUser(nickName, totalScore);
                }
                else {
                    board.setHitTheLastMole(false, 0);
                    match.setScoreToUser(nickName, totalScore);
                }
                board.setNotFirstRound();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    private Emitter.Listener onNewMole = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            System.out.println("New mole");
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
//          firstuser.addScore(mole.getScore());
            hitsound.play(1);
            mole.finish();
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

        //checkTouch(touch_x, touch_y);
        //mole.setPos(touch_x, touch_y);
        return true;
    }

    public void receiveSocket(int mole, int img){
        board.setMole(mole, img);
        if(img == 0){
            speech.play();
        }
    }

    /** The main update method **/
    public void update(float delta) {
        processInput();
        //mole.update(delta);
    }

    private void processInput() {

    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
