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

    Array grid = new Array();
    List<Mole> moles;
    private Texture background;

    public enum Theme{
        Kardashian, President
    }

    public Board(Theme theme){

        moles = new ArrayList<Mole>();
        //Choose correct background for current theme
        background = new Texture(Gdx.files.internal("background.png"));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(new Mole(new Vector2(i, j)), theme);
                //grid.get(i+j).setMoleImg(blabla.png);

            }
        }


    }

    public void setMole(Mole mole){

    }

    public Array getMoles(){
        return grid;
    }

    public int getX(){

    }

    public int getY(){

    }

    public boolean isMole(){

    }

    public void hideMole(){

    }

    public void showMole(){

    }




}
