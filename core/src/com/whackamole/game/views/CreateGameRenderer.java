package com.whackamole.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.utils.*;


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
    private int screenHeight, screenWidth;
    private StageExtensionKeyboard stage;


    public CreateGameRenderer(CreateGame createGame, boolean isJoinGame) {
        this.createGame = createGame;
        screenHeight = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        this.isJoinGame = isJoinGame;
    }


    public void loadRenderer(StageExtensionKeyboard stage) {
        this.stage = stage;
        loadTextures();
    }


    public void render() {

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenWidth, screenHeight);
        if(createGame.isInvalidGameName()) {
            float verticalMargin = getVerticalMargin(invalidGameNameText);
            stage.getBatch().draw(invalidGameNameText, verticalMargin, (screenHeight*5/10 - screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
        }
        else if(createGame.gameNameAlreadyExists()) {
            float verticalMargin = getVerticalMargin(gameNameAlreadyExistsText);
            stage.getBatch().draw(gameNameAlreadyExistsText, verticalMargin, (screenHeight*5/10- screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
        }
        else if(isJoinGame && createGame.noGameWithNameExists()) {
            float verticalMargin = getVerticalMargin(noGameWithNameExistsText);
            stage.getBatch().draw(noGameWithNameExistsText, verticalMargin, (screenHeight*5/10 - screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
        }
        else if(isJoinGame && createGame.gameIsFull()) {
            float verticalMargin = getVerticalMargin(gameIsFullText);
            stage.getBatch().draw(gameIsFullText, verticalMargin, (screenHeight*5/10 - screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
        }
        else if(createGame.isUnableToConnect()) {
            float vericalMargin = getVerticalMargin(unableToConnectText);
            stage.getBatch().draw(unableToConnectText, vericalMargin, (screenHeight*5/10- screenHeight*Constants.unableToConnectHeightRatio*3/2),
                    screenWidth*Constants.unableToConnectWidthRatio, screenHeight*Constants.unableToConnectHeightRatio);
        }
        if(createGame.isInvalidNickName()) {
            float verticalMargin = getVerticalMargin(invalidNickNameText);
            stage.getBatch().draw(invalidNickNameText, verticalMargin, (screenHeight*7/10 - screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
        }
        else if(isJoinGame && createGame.nickNameIsTaken()) {
            float verticalMargin = getVerticalMargin(gameNameAlreadyExistsText);
            stage.getBatch().draw(nickNameTakenText, verticalMargin, (screenHeight*7/10 - screenHeight*Constants.errorTextHeightRatio*3/2),
                    screenWidth*Constants.errorTextWidthRatio, screenHeight*Constants.errorTextHeightRatio);
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
        float textureWidth = screenWidth*Constants.errorTextWidthRatio;
        return (canvasWidth - textureWidth)/2;
    }

}
