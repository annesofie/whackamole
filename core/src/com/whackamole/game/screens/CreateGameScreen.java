package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.whackamole.game.controller.CreateGameController;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.Match;
import com.whackamole.game.utils.Constants;
import com.whackamole.game.utils.StageExtensionKeyboard;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.CreateGameRenderer;


public class CreateGameScreen implements Screen{

    private final ScreenController screenController;
    private CreateGame createGame;
    private CreateGameController controller;
    private CreateGameRenderer renderer;
    private TextField textFieldGameName;
    private TextField textFieldNickName;
    private boolean joinGame;
    private StageExtensionKeyboard stage;
    private Skin skin;

    public CreateGameScreen(final ScreenController screenController, boolean joinGame) {
        this.screenController = screenController;
        this.joinGame = joinGame;
        this.createGame = new CreateGame();
        this.renderer = new CreateGameRenderer(createGame, joinGame);
        this.controller = new CreateGameController(createGame, screenController);
        this.stage = StageExtensionKeyboard.getCleanInstance();
        this.skin = new Skin();

        controller.loadController();
        renderer.loadRenderer(loadActors());

        if(joinGame) {
            Match.startNewMatch();
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }

    private StageExtensionKeyboard loadActors() {

        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = Gdx.graphics.getWidth();
        float btnYPos = ((float)1/4 * screenHeight);

        skin.add("textfield", Assets.manager.get(Assets.TEXTFIELD, Texture.class));
        skin.add("cursor", Assets.manager.get(Assets.CURSOR, Texture.class));
        skin.add("btnNotClicked", Assets.manager.get(Assets.ENTERBTN, Texture.class));
        skin.add("btnClicked", Assets.manager.get(Assets.ENTERBTNCLICKED, Texture.class));
        skin.add("returnBtn", Assets.manager.get(Assets.BACK_BTN, Texture.class));

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        ImageButton btn = new ImageButton(skin.getDrawable(("btnNotClicked")), skin.getDrawable("btnClicked"));
        Drawable textFieldBackground = skin.getDrawable("textfield");
        Drawable cursor = skin.getDrawable("cursor");

        // Textfield styling
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        BitmapFont font = Assets.manager.get(Assets.TEXTFIELD_FONT);
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = textFieldBackground;
        textFieldStyle.cursor = cursor;
        textFieldStyle.cursor.setMinWidth(2f);
        textFieldStyle.messageFont = font;
        textFieldStyle.messageFontColor = Color.WHITE;

        // Create a textfield with the styling defined above
        textFieldGameName = new TextField("", textFieldStyle);
        textFieldNickName = new TextField("", textFieldStyle);


        String messageTextGameName = "Enter game name";
        String messageTextNickName = "Enter nickname";
        textFieldGameName.setMessageText(messageTextGameName);
        textFieldNickName.setMessageText(messageTextNickName);

        // Set sizes to scale with screen dimensions nicely
        btn.getCells().get(0).size(screenWidth*Constants.menuButtonWidthRatio, screenHeight*Constants.menuButtonHeightRatio);
        returnButton.getCells().get(0).size(screenWidth*Constants.returnButtonWidthRatio, screenWidth*Constants.returnButtonHeightRatio);
        textFieldGameName.setSize(screenWidth*Constants.textfieldWidthRatio, screenHeight*Constants.textfieldHeightRatio);
        textFieldNickName.setSize(screenWidth*Constants.textfieldWidthRatio, screenHeight*Constants.textfieldHeightRatio);

        returnButton.setPosition(returnButton.getWidth(), screenHeight - returnButton.getHeight()*2);
        btn.setPosition(getLeftMargin(btn.getWidth()), btnYPos);
        textFieldGameName.setPosition(getLeftMargin(textFieldGameName.getWidth()), screenHeight*5/10);
        textFieldNickName.setPosition(getLeftMargin(textFieldNickName.getWidth()), screenHeight*7/10);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String gameName = textFieldGameName.getText();
                String nickName = textFieldNickName.getText();
                boolean isValidGameName = controller.isValidGameName(gameName);
                boolean isValidNickName = controller.isValidNickName(nickName);
                if(isValidGameName && isValidNickName) {
                    if(joinGame) {
                        controller.joinGame(gameName, nickName);
                    }
                    else {
                        controller.createGame(gameName, nickName);
                    }

                }
            }
        });

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToMainMenuScreen();
            }
        });

        // Add actors
        stage.addActor(btn);
        stage.addActor(textFieldGameName);
        stage.addActor(textFieldNickName);
        stage.addActor(returnButton);

        return stage;
    }


    public float getLeftMargin(float width) {
        float screenWidth = Gdx.graphics.getWidth();
        return (screenWidth - width)/2;
    }



    @Override
    public void hide() {
        dispose();
    }


    @Override
    public void dispose() {
        controller.dispose();
    }


    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}
