package com.whackamole.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Lars on 16/04/16.
 */
public class StageExtensionKeyboard extends Stage {

    /**
     *  Singleton Stage class extension for hiding keyboard on background touch
     */

    private static StageExtensionKeyboard instance;

    private StageExtensionKeyboard() {
        super();
    }

    public static StageExtensionKeyboard getCleanInstance() {
        if(instance == null) {
            instance = new StageExtensionKeyboard();
        }
        instance.clear();
        return instance;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.unfocusAll();
        Gdx.input.setOnscreenKeyboardVisible(false);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    public static void disposeStage() {
        if(instance != null) {
            instance.dispose();
        }
    }

}
