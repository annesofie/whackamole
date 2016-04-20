package com.whackamole.game.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Lars on 20.04.2016.
 */
public class StageExtension extends Stage {
    /**
     *  Singleton Stage class extension that always provides a clean stage, but the same stage object.
     *  This is to avoid problems with having multiple stages, multiple places in the game.
     */

    private static StageExtension instance;

    private StageExtension() {
        super();
    }

    public static StageExtension getCleanInstance() {
        if(instance == null) {
            instance = new StageExtension();
        }
        instance.clear();
        return instance;
    }


    public static void disposeStage() {
        if(instance != null) {
            instance.dispose();
        }
    }


}
