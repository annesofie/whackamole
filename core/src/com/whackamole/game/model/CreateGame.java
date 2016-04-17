package com.whackamole.game.model;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CreateGame {

    private boolean invalidGameName;
    private boolean invalidNickName;
    private boolean gameNameAlreadyExists;

    public CreateGame() {
        invalidGameName = false;
        invalidNickName = false;
        gameNameAlreadyExists = false;
    }

    public boolean isInvalidGameName() {
        return invalidGameName;
    }

    public boolean isInvalidNickName() {
        return invalidNickName;
    }

    public boolean gameNameAlreadyExists() {
        return  gameNameAlreadyExists;
    }

    public void setInvalidGameName(boolean invalid) {
        invalidGameName = invalid;
    }

    public void setInvalidNickName(boolean invalid) {
        invalidNickName = invalid;
    }

    public void setGameNameAlreadyExists(boolean value) {
        gameNameAlreadyExists = value;
    }

}
