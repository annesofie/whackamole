package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
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


    public InstructionScreenRenderer(Instruction state) {

        this.state = state;
        this.cam = new OrthographicCamera();


    }


    public void render() {

    }



}
