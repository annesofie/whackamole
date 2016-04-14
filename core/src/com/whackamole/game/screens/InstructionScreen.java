package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.views.InstructionRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class InstructionScreen implements Screen {


    final WhackAMole game;
    private InstructionRenderer renderer;
    private Skin skin;
    private Stage stage;
    int screenWidth, screenHeight, whiteRectangleWidth, whiteRectangleHeight, btnWidth, btnHeight;

    public InstructionScreen(final WhackAMole game) {
        this.game = game;
        renderer = new InstructionRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        whiteRectangleWidth = (new Texture("WhiteRectangle.png").getWidth());
        whiteRectangleHeight = (new Texture("WhiteRectangle.png").getWidth());
        btnWidth = (new Texture("ReturnBtn.png")).getWidth();
        btnHeight = (new Texture("ReturnBtn.png")).getHeight();
    }

    @Override
    public void show() {
        renderer.loadRenderer(loadActors());
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
    }

    private Stage loadActors(){
        stage = new Stage();
        skin = new Skin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();

        skin.add("returnBtn", new Texture("ReturnBtn.png"));
        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        returnButton.setPosition(screenWidth/2 + whiteRectangleWidth/2 - btnHeight*2,screenHeight/2 + whiteRectangleHeight/2 - btnHeight*2);

        returnButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        stage.addActor(returnButton);
        Gdx.input.setInputProcessor(stage);
        return stage;
    }

    // THE REST OF THE SCREEN METHODS

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
        stage.dispose();
        skin.dispose();
    }

}