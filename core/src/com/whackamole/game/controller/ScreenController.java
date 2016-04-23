package com.whackamole.game.controller;

/**
 * Created by Lars on 12/04/16.
 */
public interface ScreenController {

    /*
        This controller interface is implemented by by the Game class to ensure that these methods are available to other classes that
        needs to switch between screens.
     */

    void goToGameScreen();
    void goToInstructionsScreen();
    void goToMainMenuScreen();
    void goToSettingsScreen();
    void goToJoinGameScreen();
    void goToCreateGameScreen();
    void goToReadyScreen();
    void goToGameOverScreen();


}
