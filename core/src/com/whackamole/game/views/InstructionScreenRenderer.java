package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.whackamole.game.model.Instruction;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionScreenRenderer {


    /**
     *  Renders the instruction screen
     *
     *
     *
     */

    private Instruction state;
    private OrthographicCamera cam;
    private SpriteBatch batch;

    public InstructionScreenRenderer(Instruction state) {
        this.state = state;
        this.cam = new OrthographicCamera();
        this.batch = new SpriteBatch();

    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(state.getInstructions(),0,0,state.getScreenWidth(),state.getScreenHeight());
        batch.draw(state.getReturnBtn(),0,state.getScreenHeight()-state.getScreenHeight());
        batch.end();
    }



}
