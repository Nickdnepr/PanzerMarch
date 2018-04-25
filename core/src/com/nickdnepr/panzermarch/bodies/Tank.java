package com.nickdnepr.panzermarch.bodies;


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

    public abstract float getX();

    public abstract float getY();

}
