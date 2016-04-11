package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Lars on 07/04/16.
 */
public class Instruction {

    private Texture instructions;
    private Texture returnBtn;

    int screenWidth;
    int screenHeight;
    int buttonWidth;
    int buttonHeight;

    public Instruction(){

        instructions = new Texture("instructionsTemp.png");
        returnBtn = new Texture("returnButtonTemp.png");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        buttonWidth = returnBtn.getWidth();
        buttonHeight = returnBtn.getHeight();
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

    public int getButtonWidth() {
        return buttonWidth;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }
}
