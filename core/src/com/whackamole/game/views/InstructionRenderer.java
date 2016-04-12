package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.model.GameSettings;
import com.whackamole.game.model.Instruction;
import com.whackamole.game.model.Theme;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionRenderer {

    private Instruction instructions;
    private GameSettings gameSettings;

    private SpriteBatch batch;

    // TEXTURES
    private Texture background;
    private Texture instructionText;
    private Texture returnbutton;

    private Theme theme;

    public InstructionRenderer(Instruction instruction, GameSettings gameSettings) {

        this.instructions = instruction;
        this.gameSettings = gameSettings;
        this.theme = gameSettings.getTheme();

        this.batch = new SpriteBatch();
    }



    public void loadRenderer() {
        loadTextures();
    }



    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(instructionText, 0, 0);
        batch.draw(returnbutton, 0, 0);
        batch.end();
    }



    public void loadTextures() {

        // Lag textures her basert på tema, instruksjoner osv.
        // Antakeligvis bare et bakgrunnsbilde med text på.
        // I render skal disse tegnes.

        background = new Texture("");
        instructionText = new Texture("");
        returnbutton = new Texture("");

    }


}
