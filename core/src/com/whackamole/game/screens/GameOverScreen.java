package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.model.FileName;
import com.whackamole.game.model.Match;
import com.whackamole.game.model.Player;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.GameOverRenderer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class GameOverScreen implements Screen {

    private final WhackAMole game;
    private GameOverRenderer renderer;
    private Skin skin;
    private StageExtension stage;
    private int screenWidth, screenHeight;
    private int returnBtnWidth, returnBtnHeight;

    public GameOverScreen(final WhackAMole game) {
        this.game = game;
        renderer = new GameOverRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        returnBtnWidth = (new Texture(FileName.RETURN_BTN.filename())).getWidth();
        returnBtnHeight = (new Texture(FileName.RETURN_BTN.filename())).getHeight();
    }


    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }


    @Override
    public void render(float delta) {
        renderer.render();
    }


    private StageExtension loadActors(){

        String highscoreList = getTextualHighScoreList();


        stage = new StageExtension();
        skin = new Skin();


        Gdx.input.setInputProcessor(stage);


        skin.add("returnBtn", new Texture(FileName.RETURN_BTN.filename()));
        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));

        returnButton.setPosition(returnBtnWidth, screenHeight - returnBtnHeight*2);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMainMenuScreen();
                dispose();
            }
        });


        stage.addActor(returnButton);
        stage.setText(highscoreList);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }

    private String getTextualHighScoreList(){
        Match match = game.getMatch();
        String highScoreList = "";
        int pos = 1;
        List<Player> playerList = match.getSortedHighScoreList();

        for(Player player : playerList){
            highScoreList += pos + ". " + player.getNickname() + " (" + player.getScore() + " points)\n";
            pos++;
        }
        return highScoreList;
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }


}