package com.whackamole.game.model;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CreateGame {

    private boolean invalidGameName;
    private boolean invalidNickName;
    private boolean gameNameAlreadyExists;
    private boolean noGameWithNameExists;
    private boolean gameIsFull;

    public CreateGame() {
        invalidGameName = false;
        invalidNickName = false;
        gameNameAlreadyExists = false;
        noGameWithNameExists = false;
        gameIsFull = false;
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

    public boolean noGameWithNameExists() {
        return noGameWithNameExists;
    }

    public void setNoGameWithNameExists(boolean value) {
        noGameWithNameExists = value;
    }

    public boolean gameIsFull() {
        return gameIsFull;
    }

    public void setGameIsFull(boolean gameIsFull) {
        this.gameIsFull = gameIsFull;
    }

}
