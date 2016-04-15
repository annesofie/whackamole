package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionRenderer implements Renderer {


    // TEXTURES
    private Texture background;
    private Texture whiteRectangle;
    private Stage stage;
    private int screenWidth, screenHeight, whiteRectangleWidth, whiteRectangleHeight, btnWidth, btnHeight;

    public InstructionRenderer(){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        whiteRectangleWidth = (new Texture("WhiteRectangle.png")).getWidth();
        whiteRectangleHeight = (new Texture("WhiteRectangle.png")).getHeight();
        btnWidth = (new Texture("ReturnBtn.png")).getWidth();
        btnHeight = (new Texture("ReturnBtn.png")).getHeight();
    }

    public void loadRenderer(Stage stage) {
        this.stage = stage;
        loadTextures();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);
        stage.getBatch().draw(whiteRectangle, screenWidth/2 - whiteRectangleWidth/2, screenHeight/2 - whiteRectangleHeight/2);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadTextures() {
        // Lag textures her basert på tema, instruksjoner osv.
        // Antakeligvis bare et bakgrunnsbilde med text på.
        // I render skal disse tegnes.
        background = new Texture("Background.png");
        whiteRectangle = new Texture("WhiteRectangle.png");
    }

}
