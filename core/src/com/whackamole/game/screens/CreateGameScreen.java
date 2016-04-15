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
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.CreateGameController;
import com.whackamole.game.model.CreateGame;
import com.whackamole.game.model.FileName;
import com.whackamole.game.views.CreateGameRenderer;


public class CreateGameScreen implements Screen{


    private final WhackAMole game;
    private CreateGame createGame;
    private CreateGameController controller;
    private CreateGameRenderer renderer;
    private TextField textField;

    Stage stage;
    Skin skin;



    public CreateGameScreen(final WhackAMole game) {

        this.game = game;

        this.createGame = new CreateGame();

        this.renderer = new CreateGameRenderer(createGame);

        this.controller = new CreateGameController(createGame);

    }


    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }


    @Override
    public void render(float delta) {
        this.renderer.render();
    }



    //TODO: Her lager vi actors og legger dem inn i en stage som vi sender videre til renderer.
    //TODO: Vi ønsket i utgangspunktet å håndtere alt som skal rendres i renderer. Men var nødt til å lage
    //TODO knapper og inputfelt her for å legge til listeners. Forslag til hvordan vi kan håndtere dette bedre?

    private Stage loadActors() {
        /*
            Loads and returns the actors that can be acted upon on the screen
         */

        stage = new Stage();
        skin = new Skin();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FileName.FONT.filename()));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 72;
        BitmapFont font72 = generator.generateFont(parameter);
        generator.dispose();

        float canvasHeight = Gdx.graphics.getHeight();
        float canvasWidth = Gdx.graphics.getWidth();
        float btnXPos = ((float)1/4 * canvasWidth);
        float btnYPos = ((float)1/3 * canvasHeight);

        skin.add("btnNotClicked", new Texture(Gdx.files.internal(FileName.CREATEGAMEBTN.filename())));
        skin.add("btnClicked", new Texture(Gdx.files.internal(FileName.CREATEGAMEBTNCLICKED.filename())));
        skin.add("textfield", new Texture(Gdx.files.internal(FileName.TEXTFIELD.filename())));
        skin.add("cursor", new Texture(Gdx.files.internal(FileName.CURSOR.filename())));

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
        textField = new TextField("", textFieldStyle);

        // Set the size and messagetext of the textfield
        textField.setSize(btn.getWidth(), btn.getHeight());
        String messageText = "Enter game name";
        textField.setMessageText(messageText);
        textField.setAlignment(1);

        // Position the actors
        btn.setPosition(btnXPos, btnYPos);
        textField.setPosition(btnXPos, canvasHeight/2);

        addClickListener(btn);

        // Add actors
        stage.addActor(btn);
        stage.addActor(textField);

        Gdx.input.setInputProcessor(stage);

        return stage;
    }



    private void addClickListener(ImageButton button) {

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String gameName = textField.getText();
                boolean isValid = controller.isValidGameName(gameName);
                if(isValid) {
                    game.goToGameScreen();
                    dispose();
                }
            }
        });
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
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
