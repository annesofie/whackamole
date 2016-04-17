package com.whackamole.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Lars on 16/04/16.
 */
public class StageExtension extends Stage {

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.unfocusAll();
        Gdx.input.setOnscreenKeyboardVisible(false);
        return super.touchDown(screenX, screenY, pointer, button);
    }


}