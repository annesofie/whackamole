package com.whackamole.game.controller;

import com.whackamole.game.screens.*;

/**
 * Created by Lars on 12/04/16.
 */
public interface ScreenController {

    /*
        This controller is implemented by WhackAMole to ensure that these methods are available to other classes that
        needs to switch between screens

     */


    void goToGameScreen();
    void goToInstructionsScreen();
    void goToMainMenuScreen();
    void goToSettingsScreen();
    void gotToNewGameScreen();
    void goToJoinGameScreen();

}
