package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.StageExtension;

/**
 * Created by Lars on 07/04/16.
 */
public class GameOverRenderer implements Renderer {



    /**
     *  Renders the game over window
     *
     *
     *
     */

    private Texture background, headline;
    private StageExtension stage;
    private int screenWidth, screenHeight;
    private int headlineWidth;
    private BitmapFont font;
    private String highScoreList;

    public GameOverRenderer() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,screenWidth,screenHeight);
        stage.getBatch().draw(headline,screenWidth/2 - headlineWidth/2,screenHeight*8/10);
        font.draw(stage.getBatch(),highScoreList,screenWidth/5,screenHeight*2/3);
        //font.draw(stage.getBatch(),highScoreList2,screenWidth/5,screenHeight*3/5);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();
    }

    private void loadTextures() {
        background = new Texture(FileName.BACKGROUND.filename());
        headline = new Texture(FileName.GAME_OVER_HEADLINE.filename());
        headlineWidth = headline.getWidth();
        highScoreList = stage.getText();
        //highScoreList1 = "Harald   :   300\nTrond   :   0";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FileName.FONT.filename()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = screenHeight/15;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
        generator.dispose();
    }


}
