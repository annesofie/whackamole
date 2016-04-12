package com.whackamole.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Board;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.whackamole.game.model.User;
import com.whackamole.game.utils.SocketRetreiver;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

public class BoardController{

    private Vector2 touchPos;
    private int touch_x, touch_y;
    private Board board;
    private Mole mole;
    private User firstuser;
    private Sound hitsound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
    private Socket socket;
    private String gameName;
    private String nickName;

    public BoardController(Board board) {
        this.board = board;
        this.mole = board.getMole();

        this.gameName = "spill1234";
        this.nickName = "Mitt kallenavn";


        SocketRetreiver retreiver = SocketRetreiver.getInstance();
        socket = retreiver.getSocket();
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected to socket");
                newGame(gameName, nickName);
            }
        });
        socket.on("new game success", onNewGameSuccess);
        socket.on("new game error", onNewGameError);
        socket.on("start game success", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                String msg = (String) args[0];
                System.out.println(msg);
            }
        });
        socket.on("start game error", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                String msg = (String) args[0];
                System.out.println(msg);
            }
        });
        socket.on("new mole", onNewMole);

    }

    private void startGame() {
        socket.emit("start game");
    }

    private void newGame(String gameName, String nickName) {
        JSONObject json = new JSONObject();
        try {
            json.put("gameName", gameName);
            json.put("nickName", nickName);
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
        }
    };

    private Emitter.Listener onNewGameSuccess = new Emitter.Listener(){


        @Override
        public void call(Object... args) {
            String msg = (String) args[0];
            System.out.println(msg);
            startGame();
        }
    };

    private Emitter.Listener onNewMole = new Emitter.Listener(){
        @Override
        public void call(Object... args) {
            System.out.println("got calles");
            JSONObject obj = (JSONObject) args[0];
            try {
                receiveSocket(obj.getInt("pos"), obj.getInt("pic"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void setMole(int pos) {

    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touch_x = screenX;
        touch_y = screenY;

        for (Mole mole: board.getCurrentMoles()) {
            if(mole.getBoundingRectangle().contains(touch_x, touch_y)){
//                firstuser.addScore(mole.getScore());
                hitsound.play(1);
                mole.finish();

            }
        }
        //checkTouch(touch_x, touch_y);
        //mole.setPos(touch_x, touch_y);
        System.out.println("touched");
        socket.emit("mole hit", gameName);
        return true;
    }

    public void receiveSocket(int mole, int img){
        this.board.addCurrentMole(mole);
        this.board.getCurrentMoles().get(0).setMoleImg(board.getImg(img), img);
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
