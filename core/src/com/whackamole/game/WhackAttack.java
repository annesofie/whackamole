package com.whackamole.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Theme;
import com.whackamole.game.screens.*;
import com.whackamole.game.utils.*;
import com.whackamole.game.views.Assets;
import io.socket.client.Socket;


public class WhackAttack extends Game implements ScreenController {

    private ScreenController screenController;

    @Override
    public void create() {

        screenController = this;

        loadDefaultPrefs();

        // Loading screen. Loads assets into memory on startup.
        LoadingScreen loadingScreen = new LoadingScreen(screenController);
        setScreen(loadingScreen);

    }


    @Override
    public void goToGameScreen() {
        setScreen(new GameScreen(screenController));
    }

    @Override
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



    // Disposes the disposable resources on app kill to avoid memory leaks.
    public void dispose() {
        Socket socket = SocketRetreiver.getInstance().getSocket();
        if(socket.connected() && Match.getCurrentMatch().isOnGoingMatch()) {
            socket.emit("left game", "");
            socket.disconnect();
        }
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
            System.out.println("Added default preference " + Prefs.NUMOFMOLES.key() + " with value " + numOfMoles);
        }
        if(!prefs.contains(Prefs.USERNAME.key())) {
            prefs.putString(Prefs.USERNAME.key(), username);
            prefs.flush();
            System.out.println("Added default preference " + Prefs.USERNAME.key() + " with value " + username);
        }
        if(!prefs.contains(Prefs.THEME.key())) {
            prefs.putInteger(Prefs.THEME.key(), themeID);
            prefs.flush();
            System.out.println("Added default preference " + Prefs.THEME.key() + " with value " + themeID);
        }
        if(!prefs.contains(Prefs.ISSOUND.key())) {
            prefs.putBoolean(Prefs.ISSOUND.key(), isSound);
            prefs.flush();
            System.out.println("Added default preference " + Prefs.ISSOUND.key() + " with value " + isSound);
        }
        if(!prefs.contains(Prefs.NUMOFPLAYERS.key())) {
            prefs.putInteger(Prefs.NUMOFPLAYERS.key(), numOfPlayers);
            prefs.flush();
            System.out.println("Added default preference " + Prefs.NUMOFPLAYERS.key() + " with value " + numOfPlayers);
        }
    }
}
