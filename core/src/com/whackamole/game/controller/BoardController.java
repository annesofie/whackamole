package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 07.04.2016.
 */

import com.badlogic.gdx.utils.Array;
import com.whackamole.game.model.Mole;
import com.whackamole.game.model.Board;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class BoardController {

    private Vector2 touchPos;
    private int touch_x, touch_y;
    private Board board;
    private Array<Mole> currentMoles;
    private Mole mole;

    public BoardController(Board board) {
        this.board = board;
        this.mole = board.getMole();
        this.currentMoles = board.getCurrentMoles();
    }

    public boolean checkTouch(int x, int y) {
        for(Mole mole : this.currentMoles) {
            if(mole.bounds.contains(x, y)) {
                System.out.println("You hit Trump!!");
                return true;
            }
            System.out.println("You missed Trump!");
        }
        return false;
    }

    public void sendTouch() {
        // Send bekreftelse til serveren at
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
