package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Mole extends ApplicationAdapter{

    public enum Theme {
        Kardashian, President
    }

    private Theme theme;
    private Texture moleImage;
    private Sound moleSound;  //A different sound when user hits different types of moles
    private Vector2 position = new Vector2();


    public Mole(Vector2 pos, Theme th) {

        position = pos;
        theme = th;

        if (theme == Theme.Kardashian) {
            moleImage = new Texture(Gdx.files.internal("kardashian.png"));
            moleSound = Gdx.audio.newSound(Gdx.files.internal("kar.mp3"));
        } else if (theme == Theme.President) {
            moleImage = new Texture(Gdx.files.internal("DonaldTrump.png"));
            moleSound = Gdx.audio.newSound(Gdx.files.internal("trump.mp3"));
        }
    }


    public Texture getMoleImage(){
        return moleImage;
    }
    public void setMoleImg(Texture img) {
        this.moleImage=img;
    }
    public Sound getMoleSound(){
        return moleSound;
    }
    public void setMoleSound(Sound msc) {
        this.moleSound = msc;
    }

}
