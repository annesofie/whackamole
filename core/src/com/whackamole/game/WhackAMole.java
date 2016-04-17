package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Match;
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
    private CreateGameScreen createGameScreen;
    private ReadyScreen readyScreen;

    private WhackAMole game;
    private Match match;
    private SpriteBatch batch;



    @Override
    public void create() {

        game = this;
        loadDefaultPrefs();

        // Models that should be available to multiple screens
        match = new Match();
        batch = new SpriteBatch();

        // Initialize all the screens
        gameScreen = new GameScreen(game);
        instructionScreen = new InstructionScreen(game);
        mainMenuScreen = new MainMenuScreen(game);
        settingsScreen = new SettingsScreen(game);
        createGameScreen = new CreateGameScreen(game, false);
        readyScreen = new ReadyScreen(game);

        // Inital screen to be displayed on app startup
        setScreen(mainMenuScreen);

    }

    @Override
    public void goToGameScreen(Screen fromScreen) {
        setScreen(gameScreen);
    }

    @Override
    public void goToInstructionsScreen(Screen fromScreen) {
        setScreen(instructionScreen);
    }

    @Override
    public void goToMainMenuScreen(Screen fromScreen) {
        setScreen(mainMenuScreen);
    }

    @Override
    public void goToSettingsScreen(Screen fromScreen) {
        setScreen(settingsScreen);
    }

    @Override
    public void goToJoinGameScreen(Screen fromScreen) {
        createGameScreen.setJoinGame(true);
        setScreen(createGameScreen);
    }

    @Override
    public void goToCreateGameScreen(Screen fromScreen) {
        createGameScreen.setJoinGame(false);
        setScreen(createGameScreen);
    }

    @Override
    public void goToReadyScreen(Screen fromScreen) {
        setScreen(readyScreen);
    }

    public void loadDefaultPrefs() {

        // Default values stored in Constants
        int numOfMoles = Constants.numOfMoles;
        String username = Constants.username;
        int themeID = Constants.themeID;
        boolean isSound = Constants.isSound;
        int numOfPlayers = Constants.numOfPlayers;

        Preferences prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        if(!prefs.contains(Prefs.NUMOFMOLES.key())) {
            prefs.putInteger(Prefs.NUMOFMOLES.key(), numOfMoles);
            prefs.flush();
        }
        if(!prefs.contains(Prefs.USERNAME.key())) {
            prefs.putString(Prefs.USERNAME.key(), username);
            prefs.flush();
        }
        if(!prefs.contains(Prefs.THEME.key())) {
            prefs.putInteger(Prefs.THEME.key(), themeID);
            prefs.flush();
        }
        if(!prefs.contains(Prefs.ISSOUND.key())) {
            prefs.putBoolean(Prefs.ISSOUND.key(), isSound);
            prefs.flush();
        }
        if(!prefs.contains(Prefs.NUMOFPLAYERS.key())) {
            prefs.putInteger(Prefs.NUMOFPLAYERS.key(), numOfPlayers);
            prefs.flush();
        }
    }

    public Match getMatch() {
        return match;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public void reloadBoardRenderer() {
        this.gameScreen.loadView();
    }

}
