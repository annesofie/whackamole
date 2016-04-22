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

public class Mole extends Sprite{

    private Vector2 position;
    private int height = Gdx.graphics.getHeight();
    private int width = Gdx.graphics.getWidth();
    private int moleImageId = 0;
    private int location;
    private float dt, hiddenposition, shownposition;
    private boolean hidden;
    private boolean finished;

    Rectangle rect;

    public Mole(Vector2 pos, int location) {
        this.location = location;
        this.shownposition = pos.y;
        this.hiddenposition = pos.y - height*33/160;
        this.position = setPos(pos.x, hiddenposition);
        this.dt = 0;
        this.hidden = true;
        this.finished = false;

        setBoundingRectangle();
    }

    public void update(float time) {
        if(this.dt < 0.145f && this.hidden) {
            show();
            this.dt += time;
        }
    }

    public void setMoleImageId(int id) {
        this.moleImageId = id;
    }

    public int getMoleImageId() {
        return moleImageId;
    }

    public int getLocation() {
        return location;
    }

    public Vector2 setPos(float x, float y) {
        return new Vector2(x,y);
    }

    public void finish(){
        this.finished = true;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void reset(){
        this.finished = false;
        this.position = setPos(position.x, hiddenposition);
        this.dt = 0;
    }

    public boolean finished(){
        return finished;
    }

    public Vector2 getPosition(){
        return position;
    }

    public boolean isHidden(){
        return this.hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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
            this.position = setPos(this.position.x, this.position.y + height*33/1600.0f);
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
