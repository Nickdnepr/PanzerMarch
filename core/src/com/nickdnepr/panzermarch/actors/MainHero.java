package com.nickdnepr.panzermarch.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainHero extends Actor {

    private Sprite sprite;

    public MainHero() {
        sprite = new Sprite(new Texture("tank-m4.png"));
//        sprite.setPosition(getX(), getY());
        setBounds(0, 0, sprite.getWidth(), sprite.getHeight());
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("clicked");
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        sprite.setPosition(getX(), getY());

        super.act(delta);
    }
}
