package com.whackamole.game.model;

/**
 * Created by AnneSofie on 04.04.2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Mole extends Sprite{




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




    private Texture moleImage;
    private Sound moleSound;  //A different sound when user hits different types of moles
    private Vector2 position;
    private int height = Gdx.graphics.getHeight(), width = Gdx.graphics.getWidth(), location;
    private float dt, hiddenposition, shownposition, timeLimit = 2;
    //private float hidespeed = 1000.0f;
    private boolean hidden, finished = false;
    Rectangle rect;
    private static Array<Texture> imgList;
   // private Rectangle hitBox;

    public Mole(Vector2 pos, Theme th, int location) {

        //this.position = pos;
        this.shownposition = pos.y;
        this.location = location;
        this.hiddenposition = pos.y - height*33/160;
        this.position = setPos(pos.x, hiddenposition);
        this.hidden = true;
        this.dt=0;
        setBoundingRectangle();

    }

    public void update(float time){


        if(this.dt < 0.145f && this.hidden) {
            show();
            System.out.println("show()");
            //this.hidden = false;

            // { else if (this.dt < timeLimit) {
            //this.hidden = false;
//        }else if (this.dt >= timeLimit && this.dt < timeLimit + 0.145f){
//            hide();
//            this.hidden = true;
//        } else finished = true;
            this.dt += time;
        }
    }


    public Vector2 setPos(float x, float y) {
        return new Vector2(x,y);
    }

    public void finish(){
        this.finished = true;
    }

    public void reset(){
        this.finished = false;
        this.moleImage = null;
    }

    public float getTimeLimit(){
        return timeLimit;
    }

    public void setTimeLimit(float timeLimit){
        this.timeLimit =  timeLimit;
    }



    public void setHiddenposition(){
        this.position.y = hiddenposition;
    }

    public void setShownposition(){
        this.position.y = shownposition;
    }

    public boolean getFinished(){
        return finished;
    }

    public Texture getMoleImage(){
        return moleImage;
    }

    public boolean hidden(){
        return this.hidden;
    }

    public void setHidden(){
        this.hidden = true;
    }

    public void setShown(){
        this.hidden = false;
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

    public void hide(){
        if(this.position.y > hiddenposition){
            this.position.y = this.position.y - height*33/1600.0f;
        }
        else {
            this.hidden = true;
            this.position.y = hiddenposition;}
    }

    public void show(){
        if(this.position.y < shownposition){
            this.position.y = this.position.y + height*33/1600.0f;
        }
        else {
            this.hidden = false;
            this.position.y = shownposition;}
    }

    public void setBoundingRectangle(){
        this.rect = new Rectangle(this.position.x, this.shownposition, height*33/160, width*17/60);
    }

    public Rectangle getBoundingRectangle(){
        return this.rect;
    }



}
