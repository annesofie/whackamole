package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MainMenuRenderer {


    /**
     *  Renders the main menu
     *
     */


    private Texture background;
    private Stage stage;
    private int screenWidth, screenHeight;

    public MainMenuRenderer(){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadRenderer(Stage stage){
        this.stage = stage;
        loadTextures();
    }

    private void loadTextures(){
        background = new Texture("Background.png");
    }

}