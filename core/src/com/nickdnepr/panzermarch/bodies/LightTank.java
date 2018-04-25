package com.nickdnepr.panzermarch.bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;

public class LightTank extends Tank {

    private Body base;
    private Body wheel1;
    private Body wheel2;
    private Body wheel3;
    private Body wheel4;
    private WheelJoint wheel1Joint;
    private WheelJoint wheel2Joint;
    private WheelJoint wheel3Joint;
    private WheelJoint wheel4Joint;


    public LightTank(String type, String model, Body base, Body wheel1, Body wheel2, Body wheel3, Body wheel4, WheelJoint wheel1Joint, WheelJoint wheel2Joint, WheelJoint wheel3Joint, WheelJoint wheel4Joint) {
        super(type, model);
        this.base = base;
        this.wheel1 = wheel1;
        this.wheel2 = wheel2;
        this.wheel3 = wheel3;
        this.wheel4 = wheel4;
        this.wheel1Joint = wheel1Joint;
        this.wheel2Joint = wheel2Joint;
        this.wheel3Joint = wheel3Joint;
        this.wheel4Joint = wheel4Joint;
    }

    @Override
    public void driveForward() {
        wheel1Joint.enableMotor(true);
        wheel1Joint.setMotorSpeed(50);
        wheel2Joint.enableMotor(true);
        wheel2Joint.setMotorSpeed(50);
        wheel3Joint.enableMotor(true);
        wheel3Joint.setMotorSpeed(50);
        wheel4Joint.enableMotor(true);
        wheel4Joint.setMotorSpeed(50);
    }

    @Override
    public void driveBack() {
        wheel1Joint.enableMotor(true);
        wheel1Joint.setMotorSpeed(-50);
        wheel2Joint.enableMotor(true);
        wheel2Joint.setMotorSpeed(-50);
        wheel3Joint.enableMotor(true);
        wheel3Joint.setMotorSpeed(-50);
        wheel4Joint.enableMotor(true);
        wheel4Joint.setMotorSpeed(-50);
    }

    @Override
    public void stop() {
        wheel1Joint.enableMotor(false);
        wheel1Joint.setMotorSpeed(0);
        wheel2Joint.enableMotor(false);
        wheel2Joint.setMotorSpeed(0);
        wheel3Joint.enableMotor(false);
        wheel3Joint.setMotorSpeed(0);
        wheel4Joint.enableMotor(false);
        wheel4Joint.setMotorSpeed(0);
    }

    @Override
    public float getX() {
        return base.getPosition().x;
    }

    @Override
    public float getY() {
        return base.getPosition().y;
    }
}
