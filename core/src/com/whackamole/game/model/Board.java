package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;

public class Board {

    private Array<Mole> grid = new Array<Mole>();

    int canvasHeight, canvasWidth;
    private Mole currentMole;
    private boolean hitTheLastMole;
    private int lastMolePoints;
    private boolean first;
    private boolean isFirstMole;

    public Board(){

        this.canvasHeight = Gdx.graphics.getHeight();
        this.canvasWidth = Gdx.graphics.getWidth();
        hitTheLastMole = false;
        isFirstMole = true;
        first = true;

    }


    public void loadBoard() {
        loadGrid();
    }


    private void loadGrid() {
        // Clean the grid for moles if any
        grid.clear();

        int gridDimensions = Constants.gridDimensions;
        for (int j = 0; j < gridDimensions; j++) {
            for (int i = 0; i < gridDimensions; i++) {
                //x and y coordinates must be tweaked to match the background images.
                float xpos = (2 + gridDimensions * i)*canvasWidth/10  - 17 * canvasWidth/120;
                float ypos = (gridDimensions + gridDimensions * j)*canvasHeight/16 - canvasHeight/32;

                grid.add(new Mole(new Vector2(xpos, ypos), grid.size));
            }
        }
    }

    public void setMole(int moleLocation, int image) {
        if(isFirstMole) {
            isFirstMole = false;
        }
        Mole mole = grid.get(moleLocation);
        mole.setHidden(false);
        mole.setMoleImageId(image);
        this.currentMole = mole;
    }

    public Mole getCurrentMole(){
        return currentMole;
    }

    public void setHitTheLastMole(boolean hit, int points) {
        this.hitTheLastMole = hit;
        this.lastMolePoints = points;
    }

    public boolean hitTheLastMole() {
        return hitTheLastMole;
    }

    public int getLastMolePoints() {
        return lastMolePoints;
    }

    public void setNotFirst() {
        first = false;
    }

    public boolean first() {
        return first;
    }

    public boolean isFirstMole() {
        return isFirstMole;
    }

}
