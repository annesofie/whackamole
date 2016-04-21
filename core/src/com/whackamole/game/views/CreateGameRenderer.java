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
    private Texture unableToConnectText;
    private Texture nickNameTakenText;

    // MODEL
    private CreateGame createGame;
    private boolean isJoinGame;

    // GAME PROPERTIES
    private int canvasHeight, canvasWidth;
    private StageExtensionKeyboard stage;


    public CreateGameRenderer(CreateGame createGame, boolean isJoinGame) {
        this.createGame = createGame;
        canvasHeight = Gdx.graphics.getHeight();
        canvasWidth = Gdx.graphics.getWidth();
        this.isJoinGame = isJoinGame;
    }


    public void loadRenderer(StageExtensionKeyboard stage) {
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
        else if(isJoinGame && createGame.noGameWithNameExists()) {
            float verticalMargin = getVerticalMargin(noGameWithNameExistsText);
            stage.getBatch().draw(noGameWithNameExistsText, verticalMargin, ((canvasHeight/2) - 150), noGameWithNameExistsText.getWidth(), noGameWithNameExistsText.getHeight());
        }
        else if(isJoinGame && createGame.gameIsFull()) {
            float verticalMargin = getVerticalMargin(gameIsFullText);
            stage.getBatch().draw(gameIsFullText, verticalMargin, ((canvasHeight/2) - 150), invalidGameNameText.getWidth(), invalidGameNameText.getHeight());
        }
        else if(createGame.isUnableToConnect()) {
            float vericalMargin = getVerticalMargin(unableToConnectText);
            stage.getBatch().draw(unableToConnectText, vericalMargin, ((canvasHeight/2)- 150), gameNameAlreadyExistsText.getWidth(), gameNameAlreadyExistsText.getHeight());
        }
        if(createGame.isInvalidNickName()) {
            float verticalMargin = getVerticalMargin(invalidNickNameText);
            stage.getBatch().draw(invalidNickNameText, verticalMargin, ((canvasHeight/2) + 400 - 150), invalidNickNameText.getWidth(), invalidNickNameText.getHeight());
        }
        else if(isJoinGame && createGame.nickNameIsTaken()) {
            float verticalMargin = getVerticalMargin(gameNameAlreadyExistsText);
            stage.getBatch().draw(nickNameTakenText, verticalMargin, ((canvasHeight/2) + 400 - 150), nickNameTakenText.getWidth(), nickNameTakenText.getHeight());
        }
        stage.getBatch().end();

        stage.draw();

    }


    private void loadTextures() {

        // Setting up local references to already loaded textures
        if(isJoinGame) {
            background = Assets.manager.get(Assets.JOIN_GAME_BACKROUND, Texture.class);
        }
        else {
            background = Assets.manager.get(Assets.NEW_GAME_BACKGROUND, Texture.class);
        }
        invalidGameNameText = Assets.manager.get(Assets.INVALIDGAMENAME, Texture.class);
        invalidNickNameText = Assets.manager.get(Assets.INVALIDNICKNAME, Texture.class);
        nickNameTakenText = Assets.manager.get(Assets.NICKNAMETAKEN, Texture.class);
        gameNameAlreadyExistsText = Assets.manager.get(Assets.GAMENAMEALREADYEXISTS, Texture.class);
        noGameWithNameExistsText = Assets.manager.get(Assets.NOGAMEWITHNAMEEXISTS, Texture.class);
        gameIsFullText = Assets.manager.get(Assets.GAMEISFULL, Texture.class);
        unableToConnectText = Assets.manager.get(Assets.UNABLETOCONNECT, Texture.class);
    }


    public float getVerticalMargin(Texture texture) {
        float canvasWidth = Gdx.graphics.getWidth();
        float textureWidth = (float) texture.getWidth();
        return (canvasWidth - textureWidth)/2;
    }

}
