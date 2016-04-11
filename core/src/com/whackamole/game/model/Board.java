package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Board {

    private Array<Mole> grid = new Array<Mole>(), currentMoles = new Array<Mole>();
    private Texture b1, b2, b3, b4, hs;
    private Texture background;
    int height, width;
    Theme theme;
    private String filepath;

    public Board(Theme theme){

       // moles = new ArrayList<Mole>();
        //Choose correct background for current theme
        //background = new Texture(Gdx.files.internal("background.png"));
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.theme = theme;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {

                grid.add(new Mole(new Vector2((2+3*i)*width/10  - 17*width/120, (3 + 3*j)*height/16 - height/32),
                        theme, grid.size));
            }
        }
        this.filepath = theme.path();

    }

    public void addCurrentMole(Mole mole){
        currentMoles.add(mole);
    }

    public void removeCurrentMole(Mole mole){
        currentMoles.removeValue(mole, false);
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

    public int getHeight(){
        return height;
    }

    public Mole getMole() {
        return grid.get(0);
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

    public Theme getTheme(){
        return theme;
    }







}
