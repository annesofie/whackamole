package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.screens.*;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.FontGenerator;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.views.Assets;
import net.dermetfan.gdx.assets.AnnotationAssetManager;

/**
 * Created by Lars on 07/04/16.
 */
public class WhackAMole extends Game implements ScreenController {

    private ScreenController screenController;
    private Stage stage;

    @Override
    public void create() {


        stage = new Stage();
        screenController = this;
        loadDefaultPrefs();
        loadAssets();

        //TODO: Midlertidig løsning pga. et rendringproblem i gamescreen
        FontGenerator.generateKardFont();
        FontGenerator.generatePresFont();


        // Initial screen to be displayed on app startup
        goToMainMenuScreen();
    }

    @Override
    public void goToGameScreen() {
        setScreen(new GameScreen(screenController, stage));
    }

    @Override
    public void goToInstructionsScreen() {
        setScreen(new InstructionScreen(screenController, stage));
    }

    @Override
    public void goToMainMenuScreen() {
        setScreen(new MainMenuScreen(screenController, stage));
    }

    @Override
    public void goToSettingsScreen() {
        setScreen(new SettingsScreen(screenController, stage));
    }

    @Override
    public void goToJoinGameScreen() {
        setScreen(new CreateGameScreen(screenController, true, stage));
    }

    @Override
    public void goToCreateGameScreen() {
        setScreen(new CreateGameScreen(screenController, false, stage));
    }

    @Override
    public void goToReadyScreen() {
        setScreen(new ReadyScreen(screenController, stage));
    }


    public void loadAssets() {
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


    //TODO: USE THIS TO DISPOSE ALL ASSETS ON APP QUIT
    @Override
    public void dispose() {
        Assets.dispose();
        super.dispose();
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
}
