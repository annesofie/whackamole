package com.whackamole.game.model;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CreateGame {

    private boolean invalidGameName;

    public CreateGame() {
        invalidGameName = false;
    }

    public boolean isInvalidGameName() {
        return invalidGameName;
    }

    public void setInvalidGameName(boolean invalid) {
        invalidGameName = invalid;
    }

}
