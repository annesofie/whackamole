package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.whackamole.game.controller.BoardController;
import com.whackamole.game.controller.MainMenuController;
import com.whackamole.game.model.GameSettings;
import com.whackamole.game.model.Instruction;
import com.whackamole.game.model.MainMenu;
import com.whackamole.game.controller.SocketController;
import com.whackamole.game.screens.GameScreen;
import com.whackamole.game.screens.InstructionScreen;
import com.whackamole.game.screens.MainMenuScreen;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game {




    GameSettings gameSettings;









    @Override
    public void create() {


        // Game settings initialized with default values
        gameSettings = new GameSettings();

        




        // Initilizes GameScreen, calls show() to make it the active screen and runs render every subsequent cycle
        //setScreen(new GameScreen());

        //setScreen(new MainMenuScreen(this));
        setScreen(new GameScreen(this));

    }

}
