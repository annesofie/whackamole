package com.whackamole.game.controller;


import com.whackamole.game.model.CreateGame;

/**
 * Created by Lars on 13/04/16.
 */
public class CreateGameController {


    CreateGame createGame;

    public CreateGameController(CreateGame createGame) {
        this.createGame = createGame;

    }

    public boolean isValidGameName(String gameName) {
        if(gameName.trim().length() == 0 || gameName.length() < 3) {
            createGame.setInvalidGameName(true);
            return false;

        }
        else {
            createGame.setInvalidGameName(false);
            return true;
        }

    }

}
