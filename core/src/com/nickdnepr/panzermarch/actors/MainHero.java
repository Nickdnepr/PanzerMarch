package com.nickdnepr.panzermarch.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nickdnepr.panzermarch.bodies.Tank;

public class MainHero extends Actor {

    private Sprite sprite;
    private Sprite fire;
    private Tank tank;
    private boolean drivingLeft;
    private boolean drivingRight;
    private float burningPeriodTime = 0;
    private float totalBurningTime = 0;


    public MainHero(Tank tank) {
        this.tank = tank;
        fire = new Sprite(new Texture("fire.png"));
        fire.setBounds(tank.getX(), tank.getY(), 3, 3);
        fire.setOrigin(fire.getWidth() / 2, fire.getHeight() / 2);
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

    public void shoot() {
        tank.shoot();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (tank.isBurning()) {
            fire.setPosition(tank.getX() - 3, tank.getY());
            fire.draw(batch);
        }
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
        if (tank.isBurning()) {
            burningPeriodTime += delta;
            totalBurningTime += delta;
            if (burningPeriodTime >= 1) {
                tank.burn();
                burningPeriodTime = 0;
            }
            if (totalBurningTime > 5) {
                tank.stopFire();
            }
        } else {
            totalBurningTime = 0;
            burningPeriodTime = 0;
        }


        super.act(delta);
    }

    public Tank getTank() {
        return tank;
    }
}
