package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;

/**
 * Created by Lars on 23.04.2016.
 */
public class LoadingScreen implements Screen {

    private ScreenController screenController;
    private float screenHeight;
    private float screenWidth;
    private BitmapFont font;
    private StageExtension stage;
    private float progress;
    private Texture background;


    public LoadingScreen(ScreenController screenController) {
        this.screenController = screenController;
        this.stage = StageExtension.getCleanInstance();
        this.progress = 0;

        this.screenHeight = Gdx.graphics.getHeight();
        this.screenWidth = Gdx.graphics.getWidth();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Assets.SETTINGS_FONT));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = (int)Math.ceil(screenHeight/25);
        params.minFilter = Texture.TextureFilter.Linear;
        params.magFilter = Texture.TextureFilter.Linear;
        params.color = Color.BLACK;

        font = generator.generateFont(params);
        generator.dispose();

        System.out.println("Started loading assets...");
        loadTextures();
        loadFontAssets();
        loadAndInitializeAllAssets();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        if(progress >= (float)100){
            System.out.println("Done loading assets...");
            screenController.goToMainMenuScreen();
        }
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, screenWidth, screenHeight);
        font.draw(stage.getBatch(), "LOADING\n" + String.format("%.0f", progress) + "%", screenWidth/3, screenHeight/2);
        stage.getBatch().end();
        stage.draw();
    }

    public void update(float delta) {
            Assets.manager.update();
            progress = Assets.manager.getProgress() * 100;
    }

    // Loads TrueType fonts that are used to render nicely scaled text dynamically at runtime.
    public void loadFontAssets() {
        // Font assets for renderer
        Assets.generateThemeBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/39), Assets.PRES_FONT_GAME);
        Assets.generateThemeBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/39), Assets.KARD_FONT_GAME);
        Assets.generateThemeBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/35), Assets.PRES_FONT_READY);
        Assets.generateThemeBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/35), Assets.KARD_FONT_READY);
        Assets.generateThemeBitmapFont(Theme.KARDASHIAN, (float)(Gdx.graphics.getHeight()/35), Assets.KARD_GET_READY_FONT);
        Assets.generateThemeBitmapFont(Theme.PRESIDENTIAL, (float)(Gdx.graphics.getHeight()/35), Assets.PRES_GET_READY_FONT);
        Assets.generatePlainBitmapFont((float)(Gdx.graphics.getHeight()/35), Assets.GAME_OVER_FONT);
        Assets.generatePlainBitmapFont((float)(Gdx.graphics.getHeight()/30), Assets.SETTINGS_FONT);
        Assets.generatePlainBitmapFont((float)(Gdx.graphics.getHeight()/35), Assets.TEXTFIELD_FONT);
    }


    // Loads and initializes all assets and saves them in-memory for good overall performance and switching between screens.
    public void loadAndInitializeAllAssets() {
        Assets.manager.load(Assets.class);
    }

    public void loadTextures() {
        background = new Texture(Gdx.files.internal(Assets.LOADING_BACKROUND));
    }


    @Override public void hide() {
        dispose();
    }
    @Override public void dispose() {
        font.dispose();
        background.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}

}
