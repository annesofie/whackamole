package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.whackamole.game.utils.Constants;

public class GameSettings extends ApplicationAdapter{


    // Default values;
    private int numOfMoles = Constants.numOfMoles;
    private Theme theme = Constants.theme;
    private boolean isSound = Constants.isSound;


    public GameSettings() {

    }

    public Theme getTheme(){
        return this.theme;
    }

    public int getNumOfMoles(){
        return numOfMoles;
    }

    public void setNumOfMoles(int num){
        numOfMoles = num;
    }

    public boolean isSound() {
        return isSound;
    }

    public void setIsSound(boolean isSound) {
        this.isSound = isSound;
    }


}
