package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.screens.*;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.utils.StageExtensionKeyboard;
import com.whackamole.game.views.Assets;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game implements ScreenController {

    private ScreenController screenController;
    private Stage stage;

    @Override
    public void create() {

        // One stage
        stage = new Stage();
        screenController = this;
        loadDefaultPrefs();
        loadFontAssets();
        loadAndInitializeAllAssets();

        // Initial screen to be displayed on app startup
        goToMainMenuScreen();
    }

    @Override
    public void goToGameScreen() {
        stage.clear();
        setScreen(new GameScreen(screenController, stage));
    }

    @Override
    public void goToInstructionsScreen() {
        stage.clear();
        setScreen(new InstructionScreen(screenController, stage));
    }

    @Override
    public void goToMainMenuScreen() {
        stage.clear();
        setScreen(new MainMenuScreen(screenController, stage));
    }

    @Override
    public void goToSettingsScreen() {
        stage.clear();
        setScreen(new SettingsScreen(screenController, stage));
    }

    @Override
    public void goToJoinGameScreen() {
        stage.clear();
        setScreen(new CreateGameScreen(screenController, true));
    }

    @Override
    public void goToCreateGameScreen() {
        stage.clear();
        setScreen(new CreateGameScreen(screenController, false));
    }

    @Override
    public void goToReadyScreen() {
        stage.clear();
        setScreen(new ReadyScreen(screenController, stage));
    }


    // Loads TrueType fonts that are used to render nicely scaled text dynamically at runtime.
    public void loadFontAssets() {
        // Font assets for GameScreen
        Assets.generateBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/40), Assets.PRES_FONT_GAME);
        Assets.generateBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/40), Assets.KARD_FONT_GAME);
    }


    // Loads and initializes all assets and saves them in-memory for good overall performance and switching between screens.
    public void loadAndInitializeAllAssets() {
        System.out.println("Started loading assets...");
        Assets.manager.load(Assets.class);

        /*
        while(Assets.manager.update()) {
            float progress = Assets.manager.getProgress() * 100;
            if((progress % 10) == 0.0) {
                System.out.println(progress + "%");
            }
        }
        */

        Assets.manager.finishLoading();
        System.out.println("Done loading assets...");
    }


    // Disposes the disposable resources on app kill to avoid memory leaks.
    @Override
    public void dispose() {
        StageExtension.disposeStage();
        StageExtensionKeyboard.disposeStage();
        Assets.dispose();
        super.dispose();
    }

    // Loads the default game preferences if not already set.
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
}
