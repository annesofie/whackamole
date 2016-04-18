package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.CreateGameController;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.FileName;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.CreateGameRenderer;


public class CreateGameScreen implements Screen{


    private final WhackAMole game;
    private Screen screen;
    private CreateGame createGame;
    private CreateGameController controller;
    private CreateGameRenderer renderer;
    private TextField textFieldGameName;
    private TextField textFieldNickName;
    private boolean joinGame;
    private float canvasWidth;
    private float canvasHeight;

    StageExtension stage;
    Skin skin;



    public CreateGameScreen(final WhackAMole game, boolean joinGame) {

        this.game = game;
        this.screen = this;
        this.joinGame = joinGame;

        this.createGame = new CreateGame();

        this.renderer = new CreateGameRenderer(createGame);

        this.controller = new CreateGameController(createGame, game.getMatch(), this);

        // Temporarily moved here for performance
        renderer.loadRenderer(loadActors());

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }



    //TODO: Her lager vi actors og legger dem inn i en stage som vi sender videre til renderer.
    //TODO: Vi ønsket i utgangspunktet å håndtere alt som skal rendres i renderer.
    //TODO  Var nødt til å lage knapper og inputfelt her for å håndtere button clicks her. Forslag til hvordan vi kan håndtere dette bedre?

    private StageExtension loadActors() {
        /*
            Loads and returns the actors that can be acted upon on the screen
         */

        stage = new StageExtension();
        skin = new Skin();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FileName.FONT.filename()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        BitmapFont font72 = generator.generateFont(parameter);
        generator.dispose();

        float canvasHeight = Gdx.graphics.getHeight();
        float canvasWidth = Gdx.graphics.getWidth();
        float btnXPos = ((float)1/4 * canvasWidth);
        float btnYPos = ((float)1/4 * canvasHeight);


        skin.add("textfield", new Texture(Gdx.files.internal(FileName.TEXTFIELD.filename())));
        skin.add("cursor", new Texture(Gdx.files.internal(FileName.CURSOR.filename())));
        skin.add("btnNotClicked", new Texture(Gdx.files.internal(FileName.ENTERBTN.filename())));
        skin.add("btnClicked", new Texture(Gdx.files.internal(FileName.ENTERBTNCLICKED.filename())));

        ImageButton btn = new ImageButton(skin.getDrawable(("btnNotClicked")), skin.getDrawable("btnClicked"));
        Drawable textFieldBackground = skin.getDrawable("textfield");
        Drawable cursor = skin.getDrawable("cursor");

        // Textfield styling
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font72;
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.background = textFieldBackground;
        textFieldStyle.cursor = cursor;
        textFieldStyle.cursor.setMinWidth(2f);
        textFieldStyle.messageFontColor = Color.WHITE;

        // Create a textfield with the styling defined above
        textFieldGameName = new TextField("", textFieldStyle);
        textFieldNickName = new TextField("", textFieldStyle);

        float btnWidth = btn.getWidth();
        btn.setPosition(canvasWidth/2-btnWidth/2, btnYPos);

        // Set the size and messagetext of the textfield
        textFieldGameName.setSize(btn.getWidth(), btn.getHeight());
        textFieldNickName.setSize(btn.getWidth(), btn.getHeight());
        String messageTextGameName = "Enter game name";
        String messageTextNickName = "Enter nickname";
        textFieldGameName.setMessageText(messageTextGameName);
        textFieldNickName.setMessageText(messageTextNickName);

        // Position the actors

        textFieldGameName.setPosition(canvasWidth/2-btnWidth/2, canvasHeight/2);
        textFieldNickName.setPosition(canvasWidth/2-btnWidth/2, ((canvasHeight/2) + 400));

        addClickListener(btn);

        // Add actors
        stage.addActor(btn);
        stage.addActor(textFieldGameName);
        stage.addActor(textFieldNickName);

        return stage;
    }


    public void setJoinGame(boolean joinGame) {
        this.joinGame = joinGame;
    }

    public void goToReadyScreen() {
        game.goToReadyScreen(screen);
    }

    public void reloadBoardRenderer() {
        game.reloadBoardRenderer();
    }

    public void reloadReadyRenderer() {
        game.reloadReadyRenderer();
    }

    private void addClickListener(ImageButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String gameName = textFieldGameName.getText();
                String nickName = textFieldNickName.getText();
                boolean isValidGameName = controller.isValidGameName(gameName);
                boolean isValidNickName = controller.isValidNickName(nickName);
                if(isValidGameName && isValidNickName) {
                    if(joinGame) {
                        System.out.println("Got to joinGame() in controller");
                        controller.joinGame(gameName, nickName);
                    }
                    else {
                        controller.createGame(gameName, nickName);
                    }

                }
            }
        });
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
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

}
