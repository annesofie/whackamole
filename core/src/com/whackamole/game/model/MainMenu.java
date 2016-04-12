package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MainMenu {


    private Texture background;
    private Texture createGameBtn, joinGameBtn, settingsBtn, instructionsBtn;

    int screenWidth;
    int screenHeight;
    int buttonWidth;
    int buttonHeight;



    public MainMenu(){
        background = new Texture("Bakgr.png");
        createGameBtn = new Texture("CreateGame.png");
        joinGameBtn = new Texture("JoinGame.png");
        settingsBtn = new Texture("SettingsBTN.png");
        instructionsBtn = new Texture("Instructions.png");

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

    public void setCreateGameBtn(Texture createGameBtn) {
        this.createGameBtn = createGameBtn;
    }

    public void setJoinGameBtn(Texture joinGameBtn) {
        this.joinGameBtn = joinGameBtn;
    }

    public void setSettingsBtn(Texture settingsBtn) {
        this.settingsBtn = settingsBtn;
    }

    public void setInstructionsBtn(Texture instructionsBtn) {
        this.instructionsBtn = instructionsBtn;
    }


}