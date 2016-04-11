package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.whackamole.game.screens.InstructionScreen;
import com.whackamole.game.screens.MainMenuScreen;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game {

    @Override
    public void create() {

        // Initilizes GameScreen, calls show() to make it the active screen and runs render every subsequent cycle
        setScreen(new MainMenuScreen());

    }
}
