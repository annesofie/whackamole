package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.model.*;


public class BoardRenderer {


    private Board board;
    private GameSettings gameSettings;

    // TEXTURES
    private Texture board_bottom, board_second_bottom, board_second_top, board_top, board_score;
    private Array<Texture> moleImages;

    private SpriteBatch batch;

    // GAME PROPERTIES
    private int height, width;
    private Mole currentMole;
    private boolean show;
    private Theme theme;



    public BoardRenderer(Board board, GameSettings gameSettings){

        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();

        this.board = board;
        this.gameSettings = gameSettings;
        this.theme = gameSettings.getTheme();

        this.moleImages = new Array<Texture>();

    }

    public void loadRenderer() {
        loadTextures();
        // ++ Andre ting som eventuelt må gjøres klart før spillet kan rendres
    }


    // Render to the screen
    public void render(){

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMole = board.getCurrentMole();

        batch.begin();
        batch.draw(hs, 0, 13*height/16, width, 3*height/16);
        batch.draw(b4, 0, 9*height/16, width, height/4);
        drawMole(5,9);
        batch.draw(b3, 0, 6*height/16, width, 3*height/16);
        drawMole(2,6);
        batch.draw(b2, 0, 3*height/16, width, 3*height/16);
        drawMole(-1, 3);
        batch.draw(b1, 0, 0 , width, 3*height/16);
        batch.end();

    }



    public void setMole(Mole mole){
        this.currentMole = mole;
    }

    public void loadTextures() {

        // Last inn og gjør klar alle bilder basert på valgt tema
        String filepath = theme.path();

        moleImages.clear();
        for (int i = 0; i < 6; i++) {
            moleImages.add(new Texture(Gdx.files.internal(filepath + MoleImage.getFileNameOnImageId(i))));
        }

        board_bottom = new Texture(Gdx.files.internal(filepath + FilePath.BOARD_BOTTOM.filename()));
        board_second_bottom = new Texture(Gdx.files.internal(filepath + FilePath.BOARD_SECOND_BOTTOM.filename()));
        board_second_top = new Texture(Gdx.files.internal(filepath + FilePath.BOARD_SECOND_TOP.filename()));
        board_top = new Texture(Gdx.files.internal(filepath + FilePath.BOARD_TOP.filename()));
        board_score = new Texture(Gdx.files.internal(filepath + FilePath.BOARD_SCORE.filename()));

    }

    private void drawMole(int start, int end){
        if(currentMole!= null && currentMole.getLocation() > start && currentMole.getLocation() < end){
            if(currentMole.getFinished()){
                currentMole.reset();
                //board.removeCurrentMole();
            }
            else {
                currentMole.update(0.015f);
                batch.draw(currentMole.getMoleImage(),
                        currentMole.getPosition().x,
                        currentMole.getPosition().y, 17 * width / 60, height / 6);
            }
        }
    }


    private Texture getMoleImage(Mole mole) {
        return moleImages.get(mole.getMoleImageId());
    }


}
