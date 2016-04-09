package com.whackamole.game.controller;

/**
 * Created by AnneSofie on 07.04.2016.
 */

import com.badlogic.gdx.Gdx;
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
            System.out.println("Box:");
            float xbox = mole.getHitBox().getX();
            float ybox = mole.getHitBox().getY();
            System.out.println("x: " + xbox + " - " + (xbox + mole.getHitBox().getWidth()));
            System.out.println("y: " + ybox + " - " + (ybox + mole.getHitBox().getHeight()));
            System.out.print("Hit:");
            System.out.println("x: " + x);
            System.out.println("y: " + (board.getHeight() - y));

            int canvasHeight = this.board.getHeight();

            if(mole.getHitBox().contains(x, canvasHeight - y)) {
                System.out.println("You hit the mole!!");
                return true;
            }
            System.out.println("You missed the mole!");
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
}
