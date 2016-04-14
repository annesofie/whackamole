package com.whackamole.game.model;

/**
 * Created by Lars on 12/04/16.
 */
public class CreateGame {


    private boolean buttonClicked;


    public CreateGame() {

        this.buttonClicked = false;

    }



    public void loadCreateGame() {

    }


    public void setClicked() {
        this.buttonClicked = true;
    }

    public void setNotClicked() {
        this.buttonClicked = false;
    }

    public boolean isClicked() {
        return this.buttonClicked;
    }



}
