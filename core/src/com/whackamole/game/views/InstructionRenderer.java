package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.model.Instruction;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Prefs;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionRenderer implements Renderer {

    private Instruction instructions;
    private Preferences prefs;

    private SpriteBatch batch;

    // TEXTURES
    private Texture background;
    private Texture instructionText;
    private Texture returnbutton;

    private Theme theme;

    public InstructionRenderer(Instruction instruction) {

        this.instructions = instruction;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));

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
