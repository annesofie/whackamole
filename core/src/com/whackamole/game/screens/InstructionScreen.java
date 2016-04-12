package com.whackamole.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.whackamole.game.WhackAMole;
import com.whackamole.game.controller.MainMenuController;
import com.whackamole.game.model.Instruction;
import com.whackamole.game.views.InstructionScreenRenderer;

/**
 * Created by AnneSofie on 04.04.2016.
 */
public class InstructionScreen implements Screen, InputProcessor{

    private Instruction instruction;
    private InstructionScreenRenderer renderer;
    final WhackAMole game;

    public InstructionScreen(final WhackAMole game){
        this.game = game;
        instruction = new Instruction();
        renderer = new InstructionScreenRenderer(this.instruction);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenY >= instruction.getScreenHeight()-instruction.getButtonWidth() && screenX <= instruction.getButtonWidth()){
            game.setScreen(new MainMenuScreen(game));
            dispose();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
