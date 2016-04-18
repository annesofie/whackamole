package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.utils.*;


import com.whackamole.game.utils.Prefs;


/**
 * Created by Lars on 13/04/16.
 */
public class CreateGameRenderer implements Renderer {


    // TEXTURES
    private Texture background;
    private Texture invalidGameNameText;
    private Texture invalidNickNameText;
    private Texture gameNameAlreadyExistsText;
    private Texture noGameWithNameExistsText;
    private Texture gameIsFullText;

    // MODEL
    private CreateGame createGame;

    // GAME PROPERTIES
    private Preferences prefs;
    private int canvasHeight, canvasWidth;
    private StageExtension stage;


    public CreateGameRenderer(CreateGame createGame) {
        this.createGame = createGame;
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        canvasHeight = Gdx.graphics.getHeight();
        canvasWidth = Gdx.graphics.getWidth();
    }


    public void loadRenderer(StageExtension stage) {
        this.stage = stage;
        loadTextures();
    }


    public void render() {

        // TODO: DON'T USE WIDTH OF IMAGE TO DRAW, BUT RATHER THE RATIO BETWEEN THE PICTURE AND SCREENWIDTH ETC.

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
        else if(createGame.noGameWithNameExists()) {
            float verticalMargin = getVerticalMargin(noGameWithNameExistsText);
            stage.getBatch().draw(noGameWithNameExistsText, verticalMargin, ((canvasHeight/2) - 150), invalidGameNameText.getWidth(), invalidGameNameText.getHeight());
        }
        else if(createGame.gameIsFull()) {
            float verticalMargin = getVerticalMargin(gameIsFullText);
            stage.getBatch().draw(gameIsFullText, verticalMargin, ((canvasHeight/2) - 150), invalidGameNameText.getWidth(), invalidGameNameText.getHeight());
        }
        if(createGame.isInvalidNickName()) {
            float verticalMargin = getVerticalMargin(invalidNickNameText);
            stage.getBatch().draw(invalidNickNameText, verticalMargin, ((canvasHeight/2) + 400 - 150), invalidNickNameText.getWidth(), invalidNickNameText.getHeight());
        }

        stage.getBatch().end();
        stage.draw();
    }


    private void loadTextures() {

        // Setting up local references to already loaded textures
        background = Assets.manager.get(Assets.BACKGROUND, Texture.class);
        invalidGameNameText = Assets.manager.get(Assets.INVALIDGAMENAME, Texture.class);
        invalidNickNameText = Assets.manager.get(Assets.INVALIDNICKNAME, Texture.class);
        gameNameAlreadyExistsText = Assets.manager.get(Assets.GAMENAMEALREADYEXISTS, Texture.class);
        noGameWithNameExistsText = Assets.manager.get(Assets.NOGAMEWITHNAMEEXISTS, Texture.class);
        gameIsFullText = Assets.manager.get(Assets.GAMEISFULL, Texture.class);

    }


    public float getVerticalMargin(Texture texture) {
        float canvasWidth = Gdx.graphics.getWidth();
        float textureWidth = (float) texture.getWidth();
        return (canvasWidth - textureWidth)/2;
    }




}
