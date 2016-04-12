package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.whackamole.game.utils.Constants;

public class GameSettings extends ApplicationAdapter{


    // Default values;
    private int numOfMoles = Constants.numOfMoles;
    private Theme theme = Constants.theme;

    private String gamename;
    private Sound gameMusic;


    public GameSettings() {


    }


    public String getGamename(){
        return gamename;
    }
    public void setGamename(String name){
        gamename = name;
    }
    public int getNumOfMoles(){
        return numOfMoles;
    }
    public void setNumOfMoles(int num){
        numOfMoles = num;
    }
    public Sound getGameMusic(){
        return gameMusic;
    }
    public void setGameMusic(Sound music){
        gameMusic=music;
    }


}
