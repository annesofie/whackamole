package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.User;
import com.whackamole.game.screens.*;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game implements ScreenController {


    private GameScreen gameScreen;
    private InstructionScreen instructionScreen;
    private MainMenuScreen mainMenuScreen;
    private SettingsScreen settingsScreen;
    private JoinGameScreen joinGameScreen;
    private CreateGameScreen createGameScreen;



    User user;
    WhackAMole game;



    @Override
    public void create() {

        game = this;
        loadDefaultPrefs();

        // Models that should be available to multiple screens
        user = new User();

        // Initialize all the screens
        gameScreen = new GameScreen(game);
        instructionScreen = new InstructionScreen(game);
        mainMenuScreen = new MainMenuScreen(game);
        settingsScreen = new SettingsScreen(game);
        joinGameScreen = new JoinGameScreen(game);
        createGameScreen = new CreateGameScreen(game);

        // Inital screen to be displayed on app startup
        goToCreateGameScreen();

    }



    @Override
    public void goToGameScreen() {
        setScreen(gameScreen);
    }

    @Override
    public void goToInstructionsScreen() {
        setScreen(instructionScreen);
    }

    @Override
    public void goToMainMenuScreen() {
        setScreen(mainMenuScreen);
    }

    @Override
    public void goToSettingsScreen() {
        setScreen(settingsScreen);
    }

    @Override
    public void gotToCreateGameScreen() {
        setScreen(createGameScreen);
    }

    @Override
    public void goToJoinGameScreen() {
        setScreen(joinGameScreen);
    }

    @Override
    public void goToCreateGameScreen() {
        setScreen(createGameScreen);
    }

    public void loadDefaultPrefs() {

        // Default values stored in Constants
        int numOfMoles = Constants.numOfMoles;
        String username = Constants.username;
        int themeID = Constants.themeID;
        boolean isSound = Constants.isSound;

        Preferences prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        prefs.putInteger(Prefs.NUMOFMOLES.key(), numOfMoles);
        prefs.putString(Prefs.USERNAME.key(), username);
        prefs.putInteger(Prefs.THEME.key(), themeID);
        prefs.putBoolean(Prefs.ISSOUND.key(), isSound);

        // Use flush to write preferences to disk
        prefs.flush();

    }


}
