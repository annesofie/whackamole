package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Lars on 07/04/16.
 */
public class InstructionState {

    private Texture instructions;
    private Texture returnBtn;

    int screenWidth;
    int screenHeight;

    public InstructionState(){

        instructions = new Texture("instructionsTemp.png");
        returnBtn = new Texture("returnButtonTemp");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public Texture getInstructions() {
        return instructions;
    }

    public Texture getReturnBtn() {
        return returnBtn;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
