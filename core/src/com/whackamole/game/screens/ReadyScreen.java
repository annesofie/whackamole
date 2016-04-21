package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.controller.ReadyController;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.ReadyRenderer;

import net.dermetfan.gdx.physics.box2d.PositionController;


/**
 * Created by Lars on 15/04/16.
 */
public class ReadyScreen implements Screen {


    private final ScreenController screenController;
    private StageExtension stage;
    private Skin skin;
    private ReadyRenderer renderer;
    private ReadyController controller;
    private Preferences prefs;
    private Theme theme;

    public ReadyScreen(final ScreenController screenController) {
        this.screenController = screenController;
        this.renderer = new ReadyRenderer();
        this.controller = new ReadyController(this);
        this.skin = new Skin();
        this.stage = StageExtension.getCleanInstance();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.theme = Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key()));

        renderer.loadRenderer(loadActors());
        controller.loadController();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        renderer.render();
    }


    public StageExtension loadActors() {

        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = Gdx.graphics.getWidth();
        float btnXPos = ((float)1/4 * screenWidth);
        float btnYPos = ((float)1/3 * screenHeight);



        skin.add("btnNotClicked", Assets.manager.get(Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())).path() + Assets.READY_BTN, Texture.class));
        skin.add("btnClicked", Assets.manager.get(Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())).path() + Assets.READY_BTN, Texture.class));
        skin.add("returnBtn", Assets.manager.get(Assets.LARGE_BACK_BTN, Texture.class));

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        returnButton.setPosition(returnButton.getWidth(), screenHeight - returnButton.getHeight()*2);

        ImageButton btn = new ImageButton(skin.getDrawable(("btnNotClicked")), skin.getDrawable("btnClicked"));
        btn.setName("readybtn");
        float btnWidth = 676*screenWidth/900;//btn.getWidth();
        btn.setPosition(screenWidth/2 - btnWidth/2, btnYPos);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.isReady();
            }
        });

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToMainMenuScreen();
                dispose();
            }
        });

        stage.addActor(btn);
        stage.getActors().get(0).setWidth(btnWidth);

        return stage;
    }

    public void goToGameScreen() {
        screenController.goToGameScreen();
    }


    @Override
    public void hide() {;
        //dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
    }


    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}

}
