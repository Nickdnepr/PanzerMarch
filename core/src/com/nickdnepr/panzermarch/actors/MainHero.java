package com.nickdnepr.panzermarch.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nickdnepr.panzermarch.bodies.Tank;

public class MainHero extends Actor {

    private Sprite sprite;
    private Tank tank;
    private boolean drivingLeft;
    private boolean drivingRight;

    public MainHero(Tank tank) {
        this.tank = tank;
    }

    public void driveLeft() {
        drivingLeft = true;
        drivingRight = false;
    }

    public void driveRight() {
        drivingLeft = false;
        drivingRight = true;
    }

    public void stop() {
        drivingLeft = false;
        drivingRight = false;
    }

    public void shoot(){
        tank.shoot();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (drivingRight) {
            tank.driveRight();
        } else {
            if (drivingLeft) {
                tank.driveLeft();
            } else {
                tank.stop();
            }
        }


        super.act(delta);
    }

    public Tank getTank() {
        return tank;
    }
}
