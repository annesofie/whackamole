package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Player;
import com.whackamole.game.utils.StageExtension;

import java.util.List;

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
    private Match match;

    public GameOverRenderer() {
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.match = Match.getCurrentMatch();
    }


    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0, screenWidth, screenHeight);
        font.draw(stage.getBatch(), "Results:", screenWidth/9, screenHeight*31/44);
        font.draw(stage.getBatch(), highScoreList, screenWidth/9, screenHeight*29/44);
        stage.getBatch().end();
        stage.draw();
    }

    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();
    }

    private void loadTextures() {
        background = Assets.manager.get(Assets.GAME_OVER_BACKGROUND, Texture.class);
        //headline = Assets.manager.get(Assets.GAME_OVER_HEADLINE, Texture.class);
        //headlineWidth = headline.getWidth();
        highScoreList = getTextualHighScoreList();

        font = Assets.manager.get(Assets.GAME_OVER_FONT);
    }

    private String getTextualHighScoreList(){
        String highScoreList = "";
        int pos = 1;
        List<Player> playerList = match.getSortedHighScoreList();
        for(Player player : playerList){
            highScoreList += pos + ". " + player.getNickname() + " (" + player.getScore() + " points)\n";
            pos++;
        }
        return highScoreList;
    }


}
