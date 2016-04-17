package com.whackamole.game.controller;

import com.badlogic.gdx.Screen;
import com.whackamole.game.screens.*;

/**
 * Created by Lars on 12/04/16.
 */
public interface ScreenController {

    /*
        This controller is implemented by WhackAMole to ensure that these methods are available to other classes that
        needs to switch between screens

     */


    void goToGameScreen(Screen fromScreen);
    void goToInstructionsScreen(Screen fromScreen);
    void goToMainMenuScreen(Screen fromScreen);
    void goToSettingsScreen(Screen fromScreen);
    void goToJoinGameScreen(Screen fromScreen);
    void goToCreateGameScreen(Screen fromScreen);
    void goToReadyScreen(Screen fromScreen);


}
