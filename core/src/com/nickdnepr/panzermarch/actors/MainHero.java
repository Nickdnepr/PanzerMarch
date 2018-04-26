package com.nickdnepr.panzermarch.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nickdnepr.panzermarch.bodies.Tank;

public class MainHero extends Actor {

    private Sprite sprite;
    private Tank tank;

    public MainHero(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        //sprite.setPosition(getX(), getY());
        super.act(delta);
    }

    public Tank getTank() {
        return tank;
    }
}
