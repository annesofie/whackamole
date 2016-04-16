package com.whackamole.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Board;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

public class BoardController{


    private int touch_x, touch_y;
    private Board board;
    private Sound hitsound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3")),
    speech = Gdx.audio.newSound(Gdx.files.internal(Theme.getThemeOnThemeId(Constants.themeID).path() + "speech.m4a"));
    private Socket socket;
    private String gameName;
    private String nickName;
    private Mole mole;
    private Match match;

    public BoardController(Board board, Match match) {

        this.board = board;
        this.match = match;

    }

    public void loadController() {

        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();

        this.gameName = match.getGameName();
        this.nickName = match.getThisPlayerNickName();

        SocketRetreiver socketRetreiver = SocketRetreiver.getInstance();
        socket = socketRetreiver.getSocket();

        socket.on("start game success", startGameSuccess);
        socket.on("start game error", startGameError);

        socket.on("hit success", hitSuccess);
        socket.on("hit miss", hitMiss);
        socket.on("new mole", onNewMole);
        socket.on("player score", playerScore);
    }

    private void joinGame(String gameName, String nickName) {
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("join game", json);
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


    private Emitter.Listener hitSuccess = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println(args);
        }
    };


    private Emitter.Listener hitMiss = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println(args);
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
