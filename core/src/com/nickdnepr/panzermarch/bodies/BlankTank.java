package com.nickdnepr.panzermarch.bodies;

import com.nickdnepr.panzermarch.mechanics.AimPoint;

public class BlankTank extends Tank {


    public BlankTank() {
        super(0, 0);
    }

    @Override
    public void driveRight() {

    }

    @Override
    public void driveLeft() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void shoot() {

    }

    @Override
    public void aimTo(AimPoint aimPoint) {

    }

    @Override
    public void recalculateAim() {

    }

    @Override
    public void burn() {

    }

    @Override
    public void explode() {

    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getAngle() {
        return 0;
    }
}
