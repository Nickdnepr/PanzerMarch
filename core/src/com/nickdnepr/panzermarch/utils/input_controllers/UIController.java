package com.nickdnepr.panzermarch.utils.input_controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.nickdnepr.panzermarch.actors.MainHero;
import com.nickdnepr.panzermarch.mechanics.AimPoint;

public class UIController extends InputAdapter {
    private OrthographicCamera camera;
    private MainHero hero;

    public UIController(OrthographicCamera camera, MainHero hero) {
        this.camera = camera;
        this.hero = hero;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        updateHeroAim(screenX, screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        updateHeroAim(screenX, screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    private void updateHeroAim(int screenX, int screenY) {
        Vector3 position = new Vector3(screenX, screenY, 0);
        camera.unproject(position);
        hero.getTank().aimTo(new AimPoint(position.x, position.y));
    }

    @Override
    public boolean keyUp(int keycode) {
        hero.stop();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.RIGHT) {
            hero.driveRight();

        }
        if (keycode == Input.Keys.LEFT) {
            hero.driveLeft();

        }
        if (keycode == Input.Keys.SPACE) {
            hero.getTank().shoot();
        }

        if (keycode==Input.Keys.F1){
            hero.getTank().startFire();
        }
        return true;
    }

}
