package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Button extends Actor {

    Sprite buttonSprite;

    Button(Texture texture, float x, float y, float width, float height) {
        buttonSprite = new Sprite(texture);
        setBounds(x, y, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        buttonSprite.draw(batch);
    }

    @Override
    protected void positionChanged() {
        buttonSprite.setPosition(getX(), getY());
    }

    @Override
    protected void sizeChanged() {
        buttonSprite.setSize(getWidth(), getHeight());
    }
}

