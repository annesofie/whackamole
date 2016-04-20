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

    @Override
    public void create() {

        screenController = this;
        loadDefaultPrefs();
        loadFontAssets();
        loadAndInitializeAllAssets();

        // Initial screen to be displayed on app startup
        goToMainMenuScreen();
    }

    @Override
    public void goToGameScreen() {
        setScreen(new GameScreen(screenController));
    }

    public void goToInstructionsScreen() {
        setScreen(new InstructionScreen(screenController));
    }

    @Override
    public void goToMainMenuScreen() {
        setScreen(new MainMenuScreen(screenController));
    }

    @Override
    public void goToSettingsScreen() {
        setScreen(new SettingsScreen(screenController));
    }

    @Override
    public void goToJoinGameScreen() {
        setScreen(new CreateGameScreen(screenController, true));
    }

    @Override
    public void goToCreateGameScreen() {
        setScreen(new CreateGameScreen(screenController, false));
    }

    @Override
    public void goToReadyScreen() {
        setScreen(new ReadyScreen(screenController));
    }

    @Override
    public void goToGameOverScreen() {
        setScreen(new GameOverScreen(screenController));
    }


    // Loads TrueType fonts that are used to render nicely scaled text dynamically at runtime.
    public void loadFontAssets() {
        // Font assets for renderer
        Assets.generateThemeBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/40), Assets.PRES_FONT_GAME);
        Assets.generateThemeBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/40), Assets.KARD_FONT_GAME);
        Assets.generateThemeBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/20), Assets.PRES_FONT_READY);
        Assets.generateThemeBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/20), Assets.KARD_FONT_READY);
        Assets.generatePlainBitmapFont((float)(Gdx.graphics.getHeight()/20), Assets.GAME_OVER_FONT);
        Assets.generatePlainBitmapFont((float)(Gdx.graphics.getHeight()/15), Assets.SETTINGS_FONT);
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
