package com.whackamole.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.utils.Constants;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class Board {

    private Array<Mole> grid = new Array<Mole>();
    private Array<Mole> currentMoles = new Array<Mole>();
    int canvasHeight, canvasWidth;
    Theme theme;
    private String filepath;

    private GameSettings gameSettings;

    public Board(GameSettings gameSettings){

        this.canvasHeight = Gdx.graphics.getHeight();
        this.canvasWidth = Gdx.graphics.getWidth();
        this.gameSettings = gameSettings;
        this.theme = gameSettings.getTheme();

        loadGrid();

        this.filepath = theme.path();

    }


    public void loadBoard() {
        // Init board in GameScreen show()
        loadGrid();
        // ++ andre ting som må lastes når screen byttes til GameScreen

    }


    private void loadGrid() {
        // Clean the grid for moles if any
        grid.clear();

        int gridDimensions = Constants.gridDimensions;
        for (int j = 0; j < gridDimensions; j++) {
            for (int i = 0; i < gridDimensions; i++) {
                float xpos = (2 + gridDimensions * i)*canvasWidth/10  - 17 * canvasWidth/120;
                float ypos = (gridDimensions + gridDimensions * j)*canvasHeight/16 - canvasHeight/32;

                grid.add(new Mole(new Vector2(xpos, ypos), grid.size));
            }
        }
    }

    public void setMole(int moleLocation, int image) {
        Mole mole = grid.get(moleLocation);
        mole.setMoleImageId(image);
        currentMoles.add(mole);
    }


    public void addCurrentMole(int i){
        currentMoles.add(grid.get(i));
    }

    public void removeCurrentMole(Mole mole){
        currentMoles.removeValue(mole, false);
    }

    public Array<Mole> getCurrentMoles(){
        return currentMoles;
    }

    public String getPath() {
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

    public Array<Mole> getMoles(){
        return grid;
    }

}
