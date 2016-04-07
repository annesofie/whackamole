package com.whackamole.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.whackamole.game.model.GameSettings;
import com.whackamole.game.model.GameSettingsState;

/**
 * Created by Lars on 07/04/16.
 */
public class GameSettingsRenderer {

    private GameSettingsState state;
    private OrthographicCamera cam;


    public GameSettingsRenderer(GameSettingsState state) {
        this.state = state;
        this.cam = new OrthographicCamera();
    }


    public void render() {

    }


}
