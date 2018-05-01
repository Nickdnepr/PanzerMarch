package com.nickdnepr.panzermarch.bodies;


import com.nickdnepr.panzermarch.mechanics.AimPoint;

public abstract class Tank {

    private int type;
    private int model;
    private boolean burning;

    public Tank(int type, int model) {
        this.type = type;
        this.model = model;
        burning = false;
        System.out.println("Tank constructor");
    }

    public abstract void driveRight();

    public abstract void driveLeft();

    public abstract void stop();

    public abstract void shoot();

    public abstract void aimTo(AimPoint aimPoint);

    public abstract void recalculateAim();

    public void startFire() {
        burning = true;
    }

    public void stopFire(){
        burning = false;
    }

    public abstract void burn();

    public abstract void explode();

    public abstract float getX();

    public abstract float getY();

    public abstract float getAngle();

    public int getType() {
        return type;
    }

    public int getModel() {
        return model;
    }

    public boolean isBurning() {
        return burning;
    }
}
