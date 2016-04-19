package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.model.FileName;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionRenderer implements Renderer {


    // TEXTURES
    private Texture background;
    private Texture whiteRectangle;
    private Stage stage;
    private int screenWidth, screenHeight;

    public InstructionRenderer(){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
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
        stage.getBatch().draw(whiteRectangle, screenWidth*1/10, screenHeight*2/10, screenWidth*8/10, screenHeight*6/10);
        stage.getBatch().end();
        stage.draw();
    }

    private void loadTextures() {
        // Lag textures her basert på tema, instruksjoner osv.
        // Antakeligvis bare et bakgrunnsbilde med text på.
        // I render skal disse tegnes.
        background = new Texture(FileName.BACKGROUND.filename());
        whiteRectangle = new Texture(FileName.INSTRUCTIONS_WHITE_RECTANGLE.filename());

    }

}
