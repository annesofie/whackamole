package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

public class Mole {




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



    private Theme theme;
    private Texture moleImage;
    private Sound moleSound;  //A different sound when user hits different types of moles
    private Vector2 position, velocity;
    private int height = Gdx.graphics.getHeight(), width = Gdx.graphics.getWidth(), location, hiddenposition = 200;
    private float dt = 0;
    private float hidespeed = 1000.0f;


    public Mole(Vector2 pos, Theme th, int location) {

        this.position = pos;
        this.location = location;
        theme = th;
        if (theme == Theme.KARDASHIAN) {
            moleImage = new Texture(Gdx.files.internal("trump.png"));
            //moleSound = Gdx.audio.newSound(Gdx.files.internal("kar.mp3"));
        } else if (theme == Theme.PRESIDENTIAL) {
            moleImage = new Texture(Gdx.files.internal("trump.png"));
            //moleSound = Gdx.audio.newSound(Gdx.files.internal("trump.mp3"));
        }
    }

    public void setPos(float x, float y) {
        position.set(x,y);
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

    public Vector2 getPosition(){
        return position;
    }

    public int getLocation(){
        return location;
    }

    public void hide(float dt){
        if(this.position.y > hiddenposition) {
            this.position.set(this.position.x, this.position.y - 1000.0f*dt);
        }
    }

    public void show(){

    }

}
