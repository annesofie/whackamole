package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.utils.StageExtension;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionRenderer implements Renderer {


    // TEXTURES
    private Texture background;
    private Texture instructions;
    private StageExtension stage;
    private int screenWidth, screenHeight;

    public InstructionRenderer(){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();
    }

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);
        //stage.getBatch().draw(instructions, screenWidth/10, screenHeight*2/10, screenWidth*8/10, screenHeight*6/10);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadTextures() {
        background = Assets.manager.get(Assets.INSTRUCTIONS, Texture.class);
        instructions = Assets.manager.get(Assets.INSTRUCTIONS, Texture.class);
    }

}
