package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class Mole extends ApplicationAdapter {


    /**
     *  The view:
     *  Moles should be rendered on the screen when a signal is given
     *
     *  The controller:
     *  Should maybe accept requests from the server and put a mole in the GameState?
     *
     *  The model:
     *  Should now contain a Mole on a specified location and notify the view that
     *  a mole should be rendered at that location.
     *  Some people say that the model should not be aware of the view, but other people
     *  say that it is perfectly fine to use the Observer-pattern between the model and the view. Let's check this.
     *
     *
     */



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
