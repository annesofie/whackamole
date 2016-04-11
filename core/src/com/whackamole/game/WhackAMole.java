package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.whackamole.game.controller.SocketController;
import com.whackamole.game.screens.GameScreen;
import com.whackamole.game.screens.MainMenuScreen;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game {

    private SocketController soController;
    int count = 0;

    @Override
    public void create() {
        count ++;
        String gamename = "Game" + count;  //Midlertidig før vi får mainmenu opp
        soController = new SocketController(gamename);
        // Initilizes GameScreen, calls show() to make it the active screen and runs render every subsequent cycle
        setScreen(new GameScreen());

    }


}
