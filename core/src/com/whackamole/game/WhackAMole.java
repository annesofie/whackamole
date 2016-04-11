package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.whackamole.game.controller.MainMenuController;
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

    private MainMenuScreen mainMenuScreen;
    private InstructionScreen instructionScreen;
    private MainMenuController mainMenuController;
    private MainMenu mainMenu;
    private Instruction instruction;


    private SocketController soController;
    int count = 0;

    @Override
    public void create() {
        count ++;
        String gamename = "lol" + count; //Midlertidig før vi får mainmenu opp
        System.out.println(gamename);
        soController = new SocketController(gamename);

        // Initilizes GameScreen, calls show() to make it the active screen and runs render every subsequent cycle
        //setScreen(new GameScreen());
        mainMenu = new MainMenu();
        instruction = new Instruction();
        mainMenuController = new MainMenuController(this,mainMenu);
        mainMenuScreen = new MainMenuScreen(mainMenuController,mainMenu);
        instructionScreen = new InstructionScreen(mainMenuController,instruction);

        setScreen(mainMenuScreen);
        setScreen(new GameScreen(soController));

    }

    public void goToMainMenu(){
        setScreen(mainMenuScreen);
    }

    public void goToInstructions(){
        setScreen(instructionScreen);
    }



}
