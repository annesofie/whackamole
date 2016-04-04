package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GameSettings extends ApplicationAdapter{

    public enum gameTheme {
        Kardashian, President
    }

    private String gamename;
    private int gameid;
    private int numOfMoles;
    private gameTheme theme;
    private Texture gameImg;
    private Sound gameMusic;

    public GameSettings(String name, int id, gameTheme th, int num) {

        gamename = name;
        gameid = id;
        theme = th;
        numOfMoles = num;

        if (theme == gameTheme.Kardashian){
            gameImg = new Texture(Gdx.files.internal("kardashian.png"));   // OBS The files do not yet exist
            gameMusic = Gdx.audio.newSound(Gdx.files.internal("game_kar.mp3"));
        } else if(theme == gameTheme.President){
            gameImg = new Texture(Gdx.files.internal("trump.png"));
            gameMusic = Gdx.audio.newSound(Gdx.files.internal("game_trump.mp3"));
        }

    }

    public void saveGame() {
        //Do
    }

    public String getGamename(){
        return gamename;
    }
    public void setGamename(String name){
        gamename = name;
    }
    public int getGameid(){
        return gameid;
    }
    public int getNumOfMoles(){
        return numOfMoles;
    }
    public void setNumOfMoles(int num){
        numOfMoles = num;
    }
    public gameTheme getTheme(){
        return theme;
    }
    public void setTheme(gameTheme th){
        theme = th;
    }
    public Texture getGameImg(){
        return gameImg;
    }
    public Sound getGameMusic(){
        return gameMusic;
    }
    public void setGameMusic(Sound music){
        gameMusic=music;
    }





}
