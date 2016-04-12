package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Board {

    private Array<Mole> grid = new Array<Mole>();
    private Array<Texture> imgList = new Array<Texture>();
    private Texture b1, b2, b3, b4, hs;
    private Texture background;
    int height, width;
    Theme theme;
    private String filepath;
    private Mole currentMole;

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

    public void loadImages(){
        for (int i = 0; i < 6; i++) {
            imgList.add(new Texture(Gdx.files.internal(filepath + "p" + (i + 1) +".png")));
        }
    }

    public void addCurrentMole(int i){
        this.currentMole = grid.get(i);
    }

    public Texture getImg(int i){
        if(i >=0 && i< 6){
            return imgList.get(i);
        }
        else throw new IllegalArgumentException("Texture doesn't exist!");
    }

    public void removeCurrentMole(){
        this.currentMole = null;
    }

    public Mole getCurrentMole(){
        return currentMole;
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
