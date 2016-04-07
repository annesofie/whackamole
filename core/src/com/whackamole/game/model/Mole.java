package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

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



    public enum moleTheme {
        Kardashian, President
    }

    private moleTheme theme;
    private Texture moleImage;
    private Sound moleSound;  //A different sound when user hits different types of moles
    private int positionx, positiony;


    public Mole(int pos_x, int pos_y, moleTheme th) {

        positionx=pos_x;
        positiony=pos_y;
        theme = th;

        if (theme == moleTheme.Kardashian) {
            moleImage = new Texture(Gdx.files.internal("kardashian.png")); //OBS! The files do not yet exist
            moleSound = Gdx.audio.newSound(Gdx.files.internal("kar.mp3"));
        } else if (theme == moleTheme.President) {
            moleImage = new Texture(Gdx.files.internal("DonaldTrump.png"));
            moleSound = Gdx.audio.newSound(Gdx.files.internal("trump.mp3"));
        }
    }

    public int getPositionx(){
        return positionx;
    }
    public void setPositionx(int pos_x){
        positionx=pos_x;
    }
    public int getPositiony(){
        return positiony;
    }
    public void setPositiony(int pos_y){
        positiony=pos_y;
    }
    public Texture getMoleImage(){
        return moleImage;
    }
    public Sound getMoleSound(){
        return moleSound;
    }

}
