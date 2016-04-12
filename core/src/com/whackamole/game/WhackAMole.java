package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.GameSettings;
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
    private NewGameScreen newGameScreen;
    private JoinGameScreen joinGameScreen;



    GameSettings gameSettings;
    User user;
    WhackAMole game;



    @Override
    public void create() {

        game = this;
        loadDefaultPrefs();

        // Models that should be available to multiple screens
        // WARNING: gameSettings må initialiseres først
        gameSettings = new GameSettings();
        user = new User();

        // Initialize all the screens
        gameScreen = new GameScreen(game);
        instructionScreen = new InstructionScreen(game);
        mainMenuScreen = new MainMenuScreen(game);
        settingsScreen = new SettingsScreen(game);
        newGameScreen = new NewGameScreen(game);
        joinGameScreen = new JoinGameScreen(game);


        // Inital screen to be displayed on app startup
        goToGameScreen();

    }



    // Methods that can be used to switch between the different screen instances

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
    public void gotToNewGameScreen() {
        setScreen(newGameScreen);
    }

    @Override
    public void goToJoinGameScreen() {
        setScreen(joinGameScreen);
    }




    // Getters for models that should be available to multiple classes

    /*
    public GameSettings getGameSettings() {
        return this.gameSettings;
    }

    public User getUser() {
        return this.user;
    }
    */


    public void loadDefaultPrefs() {

        // Default values stored in Constants
        int numOfMoles = Constants.numOfMoles;
        String username = Constants.username;
        int themeID = Constants.themeID;
        boolean isSound = Constants.isSound;

        Preferences prefs = Gdx.app.getPreferences(Prefs.PREFSKEY.key());

        prefs.putInteger(Prefs.NUMOFMOLESKEY.key(), numOfMoles);
        prefs.putString(Prefs.USERNAMEKEY.key(), username);
        prefs.putInteger(Prefs.THEMEKEY.key(), themeID);
        prefs.putBoolean(Prefs.ISSOUNDKEY.key(), isSound);

        // Use flush to write preferences to disk
        prefs.flush();

    }





}
