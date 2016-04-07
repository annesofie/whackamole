package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu {

    /**
     *
     *  This model should typically contain information and some logic on what should be shown
     *  when you first start the game. Think "main menu screen".
     *
     *
     *  The view:
     *  The view should be a starter screen with a menu. E.g. "Start new game", "Join game", "Settings" and "Highscores".
     *
     *  The controller:
     *  Should register clicks and do appropriate actions.
     *  In this case the controller should register when the player clicks any of the menu items and select
     *  which model the view should display next. E.g. the player clicks "Settings" and the controller changes
     *  the view to the Settings screen based on the "GameSettings" model.
     *
     *  The model:
     *  This is the model class in this case. It should contain logic and data about the lobby state.
     *
     *
     */

    private GameSettings game_setting;
    private String name;
    private int id;
    private GameSettings.gameTheme game_theme;
    private int num_of_moles;


    private Texture background;
    private Texture createGameBtn, joinGameBtn, settingsBtn, instructionsBtn;

    int screenWidth;
    int screenHeight;
    int buttonWidth;
    int buttonHeight;

    public MainMenu(){
        background = new Texture("background_temp.png");
        createGameBtn = new Texture("create_btn_temp.png");
        joinGameBtn = new Texture("join_btn_temp.png");
        settingsBtn = new Texture("settings_btn_temp.png");
        instructionsBtn = new Texture("instructions_btn_temp.png");

        game_setting = new GameSettings(name, id, game_theme, num_of_moles);
        buttonWidth = createGameBtn.getWidth();
        buttonHeight = createGameBtn.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getCreateGameBtn() {
        return createGameBtn;
    }

    public Texture getJoinGameBtn() {
        return joinGameBtn;
    }

    public Texture getSettingsBtn() {
        return settingsBtn;
    }

    public Texture getInstructionsBtn() {
        return instructionsBtn;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }
}