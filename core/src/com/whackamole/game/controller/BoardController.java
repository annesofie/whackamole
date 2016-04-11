package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 07.04.2016.
 */

import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Board;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class BoardController implements InputProcessor{

    private Vector2 touchPos;
    private int touch_x, touch_y;
    private Board board;
    private Mole mole;


    public BoardController(Board board) {
        this.board = board;
        this.mole = board.getMole();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        touch_x = screenX;
        touch_y = screenY;

        for (Mole mole: board.getCurrentMoles()) {
            if(mole.getBoundingRectangle().contains(touch_x, touch_y)){
                mole.finish();
            }
        }
        //checkTouch(touch_x, touch_y);
        mole.setPos(touch_x, touch_y);
        System.out.println("touched");
        return true;
    }

    public void receiveSocket(int mole, int img){
        this.board.addCurrentMole(mole);
        this.board.getCurrentMoles().get(0).setMoleImg(board.getImg(img));
    }

    /** The main update method **/
    public void update(float delta) {
        processInput();
        //mole.update(delta);
    }

    private void processInput() {

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}
