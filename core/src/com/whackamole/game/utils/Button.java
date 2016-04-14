package com.whackamole.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Lars on 14/04/16.
 */
public class Button extends ImageButton {

    public Button(Texture texture_up, Texture texture_down)

    {
        super(new SpriteDrawable(new Sprite(texture_up)),
                new SpriteDrawable(new Sprite(texture_down)));

    }

}
