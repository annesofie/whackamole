package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Board {

    private Array<Mole> grid = new Array<Mole>();
    //private List<Mole> moles;
    private Texture background;
    int height, width;

    public Board(Theme theme){

       // moles = new ArrayList<Mole>();
        //Choose correct background for current theme
        //background = new Texture(Gdx.files.internal("background.png"));
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(new Mole(new Vector2((2+3*i)*width/10, (3 + 3*j)*height/16), theme, grid.size));
               // grid.get(i+j).setMoleImg(new Texture(Gdx.files.internal("hull.png")));

            }
        }


    }

    public void setMole(Mole mole){

    }

    public Array<Mole> getMoles(){
        return grid;
    }

    public int getX(){
        return 0;

    }

    public int getY(){
        return 0;
    }

    public boolean isMole(){
        return true;
    }







}
