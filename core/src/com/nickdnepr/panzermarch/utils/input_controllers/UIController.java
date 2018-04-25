package com.nickdnepr.panzermarch.utils.input_controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nickdnepr.panzermarch.actors.MainHero;

public class UIController extends InputAdapter {
    private OrthographicCamera camera;
    private MainHero hero;

    public UIController(OrthographicCamera camera, MainHero hero) {
        this.camera = camera;
        this.hero = hero;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Something pressed");
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyUp(int keycode) {
        hero.getTank().stop();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("Something pressed");
        if (keycode == Input.Keys.RIGHT) {
            hero.getTank().driveBack();
            System.out.println("Drive back pressed");
        }
        if (keycode == Input.Keys.LEFT) {
            hero.getTank().driveForward();
            System.out.println("Drive forward pressed");
        }
        return true;
    }

}
