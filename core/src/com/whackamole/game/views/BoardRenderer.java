package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.utils.Array;
import com.whackamole.game.model.*;
import com.whackamole.game.utils.Prefs;

import java.util.List;


public class BoardRenderer implements Renderer {


    private Board board;
    private Preferences prefs;
    // TEXTURES
    private Texture board_bottom, board_second_bottom, board_second_top, board_top, board_score;
    private Array<Texture> moleImages;
    private SpriteBatch batch;
    // GAME PROPERTIES
    private int height, width;
    private Mole currentMole;
    private BitmapFont font;
    private Match match;



    public BoardRenderer(Board board, Match match){

        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth();
        this.batch = new SpriteBatch();
        this.match = match;

        this.board = board;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        this.moleImages = new Array<Texture>();

    }

    public void loadRenderer() {
        // Dispose to clean up if the theme was changed and loadRenderer() is run a second time.
        loadTextures();
        // ++ Andre ting som eventuelt må gjøres klart før spillet kan rendres
    }


    // Render to the screen
    public void render(){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentMole = board.getCurrentMole();
        boolean hitTheLastMole = board.hitTheLastMole();
        int lastMolePoints = board.getLastMolePoints();
        List<Player> scoreList = match.getSortedHighScoreList();

        batch.begin();

        batch.draw(board_score, 0, 13*height/16, width, 3*height/16);

        font.draw(batch, "Scores:", 100, height - 50);
        for(int i = 0; i < scoreList.size(); i++) {
            if(i >= 3) {
                break; // Breaks to avoid lists longer than 3 players
            }
            Player player = scoreList.get(i);
            String line = (i + 1) + ". " + player.getNickname() + ": " + player.getScore();
            font.draw(batch, line, 100 , height - 130 - (i*80));
        }
        if(hitTheLastMole) {
            font.draw(batch, "You hit the mole first!", width - width/2 + 50, height - 150);
            font.draw(batch, "+ " + Integer.toString(lastMolePoints) + " points.", width - width/2 + 50, height - 280);
        }
        else if (!board.firstRound()) {
            font.draw(batch, "You missed.\nNot fast enough!", width - width/2, height - 150);
        }

        batch.draw(board_score, 0, 7*height/16, width, 3*height/16);
        batch.draw(board_top, 0, 9*height/16, width, height/4);
        drawMole(5,9);
        batch.draw(board_score, 0, 5*height/16, width, 2*height/16);
        batch.draw(board_second_top, 0, 6*height/16, width, 3*height/16);
        drawMole(2,6);
        batch.draw(board_score, 0, 2*height/16, width, 2*height/16);
        batch.draw(board_second_bottom, 0, 3*height/16, width, 3*height/16);
        drawMole(-1, 3);
        batch.draw(board_bottom, 0, 0 , width, 3*height/16);
        batch.end();

    }



    public void setMole(Mole mole){
        this.currentMole = mole;
    }

    public void loadTextures() {

        // Last inn og gjør klar alle bilder basert på valgt tema

        String filepath = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())).path();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FileName.FONT.filename()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        generator.dispose();

        moleImages.clear();
        for (int i = 0; i < 6; i++) {
            moleImages.add(new Texture(Gdx.files.internal(filepath + MoleImage.getFileNameOnImageId(i))));
        }

        board_bottom = new Texture(Gdx.files.internal(filepath + FileName.BOARD_BOTTOM.filename()));
        board_second_bottom = new Texture(Gdx.files.internal(filepath + FileName.BOARD_SECOND_BOTTOM.filename()));
        board_second_top = new Texture(Gdx.files.internal(filepath + FileName.BOARD_SECOND_TOP.filename()));
        board_top = new Texture(Gdx.files.internal(filepath + FileName.BOARD_TOP.filename()));
        board_score = new Texture(Gdx.files.internal(filepath + FileName.BOARD_SCORE.filename()));


        System.out.println(board_bottom);
        System.out.println(board_second_bottom);
        System.out.println(board_second_top);
        System.out.println(board_top);

    }

    private void drawMole(int start, int end){
        if(currentMole!= null && currentMole.getLocation() > start && currentMole.getLocation() < end){
            if(currentMole.finished()){
                    currentMole.reset();
                    board.removeCurrentMole();
            }
            else {
                currentMole.update(0.015f);
                batch.draw(getMoleImage(currentMole),
                        currentMole.getPosition().x,
                        currentMole.getPosition().y, 17 * width / 60, 33*height / 160);
            }
        }
    }


    private Texture getMoleImage(Mole mole) {
        return moleImages.get(mole.getMoleImageId());
    }


}
