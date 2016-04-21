package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.whackamole.game.controller.ScreenController;
import com.whackamole.game.model.Theme;
import com.whackamole.game.utils.Prefs;
import com.whackamole.game.utils.StageExtension;
import com.whackamole.game.views.Assets;
import com.whackamole.game.views.GameSettingsRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class SettingsScreen implements Screen {


    private final ScreenController screenController;
    private Preferences prefs;
    private GameSettingsRenderer renderer;
    private Skin skin;
    private StageExtension stage;
    private float screenWidth, screenHeight, createBtnWidth, minusBtnWidth, plusBtnWidth, minusBtnHeight;
    private float theme_btn_diameter;
    private float soundBtnWidth;
    private float returnBtnWidth, returnBtnHeight;
    private CheckBox soundCheckBox;
    private CheckBox kardCheckBox;
    private CheckBox presCheckBox;


    public SettingsScreen(final ScreenController screenController) {
        this.screenController = screenController;
        this.skin = new Skin();
        this.stage = StageExtension.getCleanInstance();
        this.prefs = Gdx.app.getPreferences(Prefs.PREFS.key());
        this.renderer = new GameSettingsRenderer();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        theme_btn_diameter = screenWidth/3;//(new Texture(FileName.KARDASHIAN_THEME_BTN.filename())).getWidth();

        soundBtnWidth = 0.16f*screenWidth;//(new Texture(FileName.SOUND_ON_BTN.filename())).getWidth();
        returnBtnWidth = 4*screenWidth/90;//(new Texture(FileName.RETURN_BTN.filename())).getWidth();
        returnBtnHeight = screenHeight/40;//(new Texture(FileName.RETURN_BTN.filename())).getHeight();
        createBtnWidth = 680*screenWidth/900;
        minusBtnWidth = 193*screenWidth/900;
        minusBtnHeight = 0.08125f*screenHeight;

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


    private StageExtension loadActors(){

        Texture kardThemeBtn = Assets.manager.get(Assets.KARDASHIAN_THEME_BTN, Texture.class);
        Texture presThemeBtn = Assets.manager.get(Assets.PRESEDENTIAL_THEME_BTN, Texture.class);
        Texture kardThemeBtnSelected = Assets.manager.get(Assets.KARDASHIAN_THEME_BTN_SELECTED, Texture.class);
        Texture presThemeBtnSelected = Assets.manager.get(Assets.PRESEDENTIAL_THEME_BTN_SELECTED, Texture.class);
        Texture returnBtn = Assets.manager.get(Assets.LARGE_BACK_BTN, Texture.class);
        Texture minusBtn = Assets.manager.get(Assets.MINUSBTN, Texture.class);
        Texture minusBtnClicked = Assets.manager.get(Assets.MINUSBTNCLICKED, Texture.class);
        Texture plusBtn = Assets.manager.get(Assets.PLUSBTN, Texture.class);
        Texture plusBtnClicked = Assets.manager.get(Assets.PLUSBTNCLICKED, Texture.class);

        Texture createBtn = Assets.manager.get(Assets.PROCEED_BTN, Texture.class);
        Texture createBtnClicked = Assets.manager.get(Assets.PROCEED_BTN_CLICKED, Texture.class);

        int theme_btn_diameter = kardThemeBtn.getWidth();
        int returnBtnWidth = returnBtn.getWidth();
        int returnBtnHeight = returnBtn.getHeight();

        skin.add("returnBtn", returnBtn);
        skin.add("presThemeBtn", presThemeBtn);
        skin.add("kardThemeBtn", kardThemeBtn);
        skin.add("kardThemeBtnSelected", kardThemeBtnSelected);
        skin.add("presThemeBtnSelected", presThemeBtnSelected);
        skin.add("minusBtn", minusBtn);
        skin.add("minusBtnClicked", minusBtnClicked);
        skin.add("plusBtn", plusBtn);
        skin.add("plusBtnClicked", plusBtnClicked);
        skin.add("createBtn", createBtn);
        skin.add("createBtnClicked", createBtnClicked);

        ImageButton returnButton = new ImageButton(skin.getDrawable("returnBtn"));
        ImageButton plusButton = new ImageButton(skin.getDrawable("plusBtn"), skin.getDrawable("plusBtnClicked"));
        ImageButton minusButton = new ImageButton(skin.getDrawable("minusBtn"), skin.getDrawable("minusBtnClicked"));
        ImageButton createButton = new ImageButton(skin.getDrawable("createBtn"), skin.getDrawable("createBtnClicked"));
        //ImageButton presThemeButton = new ImageButton(skin.getDrawable("presThemeBtn"));
        //ImageButton kardThemeButton = new ImageButton(skin.getDrawable("kardThemeBtn"));

        CheckBox.CheckBoxStyle kardCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("kardThemeBtn"), skin.getDrawable("kardThemeBtnSelected"), new BitmapFont(), new Color());
        final CheckBox.CheckBoxStyle presCheckBoxStyle = new CheckBox.CheckBoxStyle(skin.getDrawable("presThemeBtn"), skin.getDrawable("presThemeBtnSelected"), new BitmapFont(), new Color());

        kardCheckBox = new CheckBox("kard", kardCheckBoxStyle);
        presCheckBox = new CheckBox("pres", presCheckBoxStyle);


        plusButton.setPosition(screenWidth/2 + plusBtn.getWidth()/2, screenHeight*17/24 - plusBtn.getHeight()*5/8);
        minusButton.setPosition(screenWidth/2 - minusBtn.getWidth()*3/2, screenHeight*17/24 - minusBtn.getHeight()*5/8);
        returnButton.setPosition(returnBtnWidth, screenHeight - returnBtnHeight*2);
        kardCheckBox.setPosition(screenWidth/2 + screenWidth/20, screenHeight/2 - screenHeight/9);
        presCheckBox.setPosition(screenWidth/2 - screenWidth/20 - theme_btn_diameter, screenHeight/2 - screenHeight/9);
        createButton.setPosition(screenWidth/2-createBtnWidth/2, screenHeight*7/24);

        /* ALTERNATIV TIL BULKEN OVER. SPØRS HVEM SOM SER BEST UT
        plusButton.setPosition(screenWidth/2 + screenWidth/3 - minusBtnWidth, screenHeight*8/12 - minusBtnHeight/2);
        minusButton.setPosition(screenWidth/2 - screenWidth/3 , screenHeight*8/12 - minusBtnHeight/2);
        returnButton.setPosition(screenWidth*9/10 - returnBtnWidth*3, screenHeight*8/10 - returnBtnHeight*3);
        createButton.setPosition(screenWidth/2-createBtnWidth/2, screenHeight*3/12);
        soundCheckBox.setPosition(screenWidth/2-soundBtnWidth/2, screenHeight/2);
        kardCheckBox.setPosition(screenWidth/2, screenHeight/2 - screenHeight/6);
        presCheckBox.setPosition(screenWidth/2 - theme_btn_diameter, screenHeight/2 - screenHeight/6);
        //presThemeButton.setPosition(screenWidth/2 - screenWidth/20 - theme_btn_diameter, screenHeight/2 - screenHeight/5);
        //kardThemeButton.setPosition(screenWidth/2 + screenWidth/20, screenHeight/2 - screenHeight/5);
        */

        if(Theme.getThemeOnThemeId(prefs.getInteger(Prefs.THEME.key())) == Theme.KARDASHIAN) {
            kardCheckBox.setChecked(true);
            presCheckBox.setChecked(false);
        }
        else {
            kardCheckBox.setChecked(false);
            presCheckBox.setChecked(true);
        }

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToMainMenuScreen();
            }
        });


        createButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenController.goToCreateGameScreen();
            }
        });

        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(prefs.getInteger(Prefs.NUMOFPLAYERS.key()) < 5){
                    prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) + 1);
                    prefs.flush();
                    System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
                }

            }
        });

        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(prefs.getInteger(Prefs.NUMOFPLAYERS.key()) > 1){ //TODO: BYTTE TIL 2 VED DEPLOY
                    prefs.putInteger(Prefs.NUMOFPLAYERS.key(), prefs.getInteger(Prefs.NUMOFPLAYERS.key()) - 1);
                    prefs.flush();
                    System.out.println(prefs.getInteger(Prefs.NUMOFPLAYERS.key()));
                }

            }
        });

        // TODO: Fikse så det ikke går ann å klikke på buttons som allerede er "checked"
        // Presedential theme button
        presCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                kardCheckBox.setChecked(false);
                prefs.putInteger(Prefs.THEME.key(), Theme.PRESIDENTIAL.getId());
                prefs.flush();
                System.out.println("Presidential theme now selected.");
            }
        });

        // Kardashian theme button
        kardCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                presCheckBox.setChecked(false);
                prefs.putInteger(Prefs.THEME.key(), Theme.KARDASHIAN.getId());
                prefs.flush();
                System.out.println("Kardashian theme now selected.");
            }
        });

//        soundCheckBox.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("soundBtnClicked");
//                if(soundCheckBox.isChecked()){
//                    //soundCheckBox.setChecked(false);
//                    prefs.putBoolean(Prefs.ISSOUND.key(), true);
//                    prefs.flush();
//                } else {
//                    //soundCheckBox.setChecked(true);
//                    prefs.putBoolean(Prefs.ISSOUND.key(), false);
//                    prefs.flush();
//                }
//            }
//        });

        stage.addActor(returnButton);
        stage.addActor(minusButton);
        stage.addActor(plusButton);
        stage.addActor(createButton);
        stage.addActor(kardCheckBox);
        stage.addActor(presCheckBox);

        setSize(stage.getActors().get(0), returnBtnWidth);
        setSize(stage.getActors().get(1), minusBtnWidth);
        setSize(stage.getActors().get(2), minusBtnWidth);
        setSize(stage.getActors().get(3), createBtnWidth);
        setSize(stage.getActors().get(4), theme_btn_diameter, theme_btn_diameter);
        setSize(stage.getActors().get(5), theme_btn_diameter, theme_btn_diameter);

        return stage;
    }

    public void setSize(Actor actor, float width){
        actor.setWidth(width);
    }

    public void setSize(Actor actor, float width, float height){
        actor.setWidth(width);
        actor.setHeight(height);
    }

    @Override
    public void hide() {
       //dispose();
    }


    @Override
    public void dispose() {
        skin.dispose();
    }

    // THE REST OF THE SCREEN METHODS
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}

}
