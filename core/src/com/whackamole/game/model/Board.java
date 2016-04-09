package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.utils.Constants;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Board {

    private Array<Mole> grid = new Array<Mole>(), currentMoles = new Array<Mole>();
    private Texture b1, b2, b3, b4, hs;
    int height, width;
    Theme theme;
    private String filepath;

    public Board(Theme theme){

        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.theme = theme;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float xpos = (2+3*i)*width/10  - 17*width/120;
                float ypos = (3 + 3*j)*height/16 - height/32;
                Vector2 vector = new Vector2(xpos, ypos);

                float hitBoxWidth = Constants.hitBoxRelativeWidth * width;
                float hitBoxHeight = Constants.hitBoxRelativeHeight * height;
                Rectangle hitBox = new Rectangle(xpos, ypos, hitBoxWidth, hitBoxHeight);

                grid.add(new Mole(vector, theme, grid.size, hitBox));
            }
        }
        this.filepath = theme.path();

    }

    public void addCurrentMole(Mole mole){
        currentMoles.add(mole);
    }

    public Array<Mole> getCurrentMoles(){
        return currentMoles;
    }

    public String getPath(){
        return this.filepath;
    }

    public void showMole(Mole mole){
        if(mole.hidden()) {
            mole.show();
        }
    }
    public void hideMole(Mole mole){
        if(!mole.hidden()) {
            mole.hide();
        }
    }

    public Mole getMole() {
        return grid.get(0);
    }

    public Array<Mole> getMoles(){
        return grid;
    }

    public int getY(){
        return 0;
    }

    public boolean isMole(){
        return true;
    }

    public Theme getTheme(){
        return theme;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

}
