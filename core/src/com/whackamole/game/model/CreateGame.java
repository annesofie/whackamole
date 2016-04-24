package com.whackamole.game.model;

public class CreateGame {

    private boolean invalidGameName;
    private boolean invalidNickName;
    private boolean gameNameAlreadyExists;
    private boolean noGameWithNameExists;
    private boolean gameIsFull;
    private boolean unableToConnect;
    private boolean nickNameTaken;
    private boolean leftGame;

    public CreateGame() {
        invalidGameName = false;
        invalidNickName = false;
        nickNameTaken = false;
        gameNameAlreadyExists = false;
        noGameWithNameExists = false;
        gameIsFull = false;
        unableToConnect = false;
        leftGame = false;
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

    public void setGameIsFull(boolean full) {
        gameIsFull = full;
    }

    public void setUnableToConnect(boolean value) {
        unableToConnect = value;
    }

    public boolean isUnableToConnect() {
        return this.unableToConnect;
    }

    public void setNickNameTaken(boolean value) {
        nickNameTaken = value;
    }

    public boolean nickNameIsTaken() {
        return this.nickNameTaken;
    }

}
