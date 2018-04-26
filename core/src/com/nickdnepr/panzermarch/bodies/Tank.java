package com.nickdnepr.panzermarch.bodies;


import com.nickdnepr.panzermarch.mechanics.AimPoint;

public abstract class Tank {

    private String type;
    private String model;

    public Tank(String type, String model) {
        this.type = type;
        this.model = model;
    }

    public abstract void driveForward();

    public abstract void driveBack();

    public abstract void stop();

    public abstract void shoot();

    public abstract void aimTo(AimPoint aimPoint);

    public abstract void recalculateAim();

    public abstract float getX();

    public abstract float getY();

    public abstract float getAngle();

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }
}
