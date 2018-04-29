package com.nickdnepr.panzermarch.actors.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nickdnepr.panzermarch.actors.MainHero;
import com.nickdnepr.panzermarch.utils.config.GameConfig;

import static com.nickdnepr.panzermarch.utils.constants.ButtonTags.*;
import static com.nickdnepr.panzermarch.utils.constants.Sizes.GlobalSizes.BUTTON_SIZE;

public class Button extends Actor {

    private Sprite sprite;
    private int tag;
    private OrthographicCamera camera;
    private MainHero hero;

    public Button(final int tag, OrthographicCamera camera, final MainHero hero) {
        this.tag = tag;
        this.camera = camera;
        this.hero = hero;
        init();
        initBounds();
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(" i was clicked");
                super.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (tag) {
                    case DRIVE_LEFT: {
                        hero.driveLeft();
                        break;
                    }
                    case DRIVE_RIGHT: {
                        hero.driveRight();
                        break;
                    }
                    case SHOOT: {
                        hero.shoot();
                        break;
                    }
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                switch (tag) {
                    case DRIVE_LEFT: {
                        hero.stop();
                        break;
                    }
                    case DRIVE_RIGHT: {
                        hero.stop();
                        break;
                    }
                }
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        initBounds();
        //System.out.println(getX() + " " + getY());
        sprite.setPosition(getX(), getY());
        sprite.draw(batch);
    }

    private void init() {
        switch (tag) {
            case DRIVE_LEFT: {
                sprite = new Sprite(new Texture("arrow.jpg"));
                sprite.setOrigin((float) (BUTTON_SIZE/2.0), (float) (BUTTON_SIZE/2.0));
                sprite.setRotation(90);
                break;
            }
            case DRIVE_RIGHT: {
                sprite = new Sprite(new Texture("arrow.jpg"));
                sprite.setOrigin((float) (BUTTON_SIZE/2.0), (float) (BUTTON_SIZE/2.0));
                sprite.setRotation(-90);
                break;
            }
            case SHOOT: {
                sprite = new Sprite(new Texture("badlogic.jpg"));
                //sprite.setOrigin(getX() + getWidth() / 2, getY() + getHeight() / 2);
                break;
            }

        }
    }

    private void initBounds() {
        switch (tag) {
            case DRIVE_LEFT: {
                setBounds(camera.position.x - camera.viewportWidth / 2 + 1, camera.position.y - camera.viewportHeight / 2 + 1, BUTTON_SIZE, BUTTON_SIZE);
                break;
            }
            case DRIVE_RIGHT: {
                setBounds(camera.position.x - camera.viewportWidth / 2 + 13, camera.position.y - camera.viewportHeight / 2 + 1, BUTTON_SIZE, BUTTON_SIZE);
                break;
            }
            case SHOOT: {
                setBounds(camera.position.x + camera.viewportWidth / 2 - 13, camera.position.y - camera.viewportHeight / 2 + 1, BUTTON_SIZE, BUTTON_SIZE);
                break;
            }
        }
    }

    public int getTag() {
        return tag;
    }
}
