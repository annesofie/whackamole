package com.whackamole.game.screens;

import com.badlogic.gdx.Screen;
import com.whackamole.game.model.InstructionState;
import com.whackamole.game.views.InstructionScreenRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class InstructionScreen implements Screen{

    InstructionState state;
    InstructionScreenRenderer renderer;

    @Override
    public void show() {
        this.state = new InstructionState();
        this.renderer = new InstructionScreenRenderer(this.state);
    }

    @Override
    public void render(float delta) {
        this.renderer.render();
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

    @Override
    public void dispose() {

    }
}
