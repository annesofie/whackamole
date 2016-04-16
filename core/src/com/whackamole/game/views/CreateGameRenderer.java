package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.*;


import com.whackamole.game.utils.Button;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.Prefs;

import javax.swing.*;


/**
 * Created by Lars on 13/04/16.
 */
public class CreateGameRenderer implements Renderer {


    // TEXTURES

    private Texture background;
    private Texture invalidGameNameText;
    private Texture invalidNickNameText;
    private Texture gameNameAlreadyExistsText;

    // MODEL
    private CreateGame createGame;

    // GAME PROPERTIES

    private Preferences prefs;
    private int canvasHeight, canvasWidth;


    public CreateGameRenderer(CreateGame createGame) {

        this.createGame = createGame;

        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());

        canvasHeight = Gdx.graphics.getHeight();
        canvasWidth = Gdx.graphics.getWidth();

    }


    StageExtension stage;

    public void loadRenderer(StageExtension stage) {

        this.stage = stage;
        loadTextures();

    }


    public void render() {

        stage.act();

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, canvasWidth, canvasHeight);
        if(createGame.isInvalidGameName()) {
            float verticalMargin = getVerticalMargin(invalidGameNameText);
            stage.getBatch().draw(invalidGameNameText, verticalMargin, ((canvasHeight/2) - 150), invalidGameNameText.getWidth(), invalidGameNameText.getHeight());
        }
        else if(createGame.gameNameAlreadyExists()) {
            float verticalMargin = getVerticalMargin(gameNameAlreadyExistsText);
            stage.getBatch().draw(gameNameAlreadyExistsText, verticalMargin, ((canvasHeight/2)- 150), gameNameAlreadyExistsText.getWidth(), gameNameAlreadyExistsText.getHeight());
        }
        if(createGame.isInvalidNickName()) {
            float verticalMargin = getVerticalMargin(invalidNickNameText);
            stage.getBatch().draw(invalidNickNameText, verticalMargin, ((canvasHeight/2) + 400 - 150), invalidNickNameText.getWidth(), invalidNickNameText.getHeight());
        }
        stage.getBatch().end();

        stage.draw();

    }


    private void loadTextures() {

        background = new Texture(Gdx.files.internal(FileName.BACKGROUND_GRASS.filename()));
        invalidGameNameText = new Texture(Gdx.files.internal(FileName.INVALIDGAMENAME.filename()));
        invalidNickNameText = new Texture(Gdx.files.internal(FileName.INVALIDNICKNAME.filename()));
        gameNameAlreadyExistsText = new Texture(Gdx.files.internal(FileName.GAMENAMEALREADYEXISTS.filename()));
    }


    public float getVerticalMargin(Texture texture) {

        float canvasWidth = Gdx.graphics.getWidth();
        float textureWidth = (float) texture.getWidth();

        float diff = canvasWidth - textureWidth;
        float margin = diff/2;

        return margin;
    }




}
