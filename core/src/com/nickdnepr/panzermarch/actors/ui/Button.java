package com.nickdnepr.panzermarch.actors.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button extends Actor {












































    private Sprite sprite;
    private String tag;
    private OrthographicCamera camera;

    public Button(Sprite sprite, String tag, OrthographicCamera camera) {
        this.sprite = sprite;
        this.tag = tag;
        this.camera = camera;
        setBounds(camera.position.x, camera.position.y, 100, 100);
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(" i was clicked");
                super.clicked(event, x, y);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(camera.position.x, camera.position.y);
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }

}
