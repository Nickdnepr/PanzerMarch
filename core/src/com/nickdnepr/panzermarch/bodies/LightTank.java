package com.nickdnepr.panzermarch.bodies;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.nickdnepr.panzermarch.mechanics.AimPoint;
import com.nickdnepr.panzermarch.mechanics.Bullet;
import com.nickdnepr.panzermarch.mechanics.Module;
import com.nickdnepr.panzermarch.utils.constants.Properties;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;

public class LightTank extends Tank {
    public static final int MOTOR_SPEED = 70;

    private Body base;
    private Body wheel1;
    private Body wheel2;
    private Body wheel3;
    private Body wheel4;
    private WheelJoint wheel1Joint;
    private WheelJoint wheel2Joint;
    private WheelJoint wheel3Joint;
    private WheelJoint wheel4Joint;
    private Body barrel;
    private RevoluteJoint barrelFixer;
    private AimPoint aimPoint;

    private Fixture engine;
    private Module eng;

    //TODO make constants for tank creation

    public LightTank(int type, int model, Body base, Body wheel1, Body wheel2, Body wheel3, Body wheel4, WheelJoint wheel1Joint, WheelJoint wheel2Joint, WheelJoint wheel3Joint, WheelJoint wheel4Joint, Body barrel, RevoluteJoint barrelFixer, Fixture engine) {
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
        this.barrel = barrel;
        this.barrelFixer = barrelFixer;
        this.engine = engine;
        this.base.setUserData(this);
        this.barrel.setUserData(this);
        eng = (Module) engine.getUserData();
        System.out.println("Local Axis " + wheel1Joint.getLocalAxisA());
        aimPoint = new AimPoint(100, 0);

    }

    @Override
    public void driveRight() {

        if (eng.getHp()<50){
            stop();
            return;
        }
        wheel1Joint.enableMotor(true);
        wheel1Joint.setMotorSpeed(-MOTOR_SPEED);
        wheel2Joint.enableMotor(true);
        wheel2Joint.setMotorSpeed(-MOTOR_SPEED);
        wheel3Joint.enableMotor(true);
        wheel3Joint.setMotorSpeed(-MOTOR_SPEED);
        wheel4Joint.enableMotor(true);
        wheel4Joint.setMotorSpeed(-MOTOR_SPEED);
    }

    @Override
    public void driveLeft() {

        if (eng.getHp()<50){
            stop();
            return;
        }

        wheel1Joint.enableMotor(true);
        wheel1Joint.setMotorSpeed(MOTOR_SPEED);
        wheel2Joint.enableMotor(true);
        wheel2Joint.setMotorSpeed(MOTOR_SPEED);
        wheel3Joint.enableMotor(true);
        wheel3Joint.setMotorSpeed(MOTOR_SPEED);
        wheel4Joint.enableMotor(true);
        wheel4Joint.setMotorSpeed(MOTOR_SPEED);
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
        wheel1.setAngularVelocity(0);
        wheel2.setAngularVelocity(0);
        wheel3.setAngularVelocity(0);
        wheel4.setAngularVelocity(0);
    }

    @Override
    public void shoot() {
        Body bullet = BodyMaker.makeCircle(base.getWorld(), (float) (barrel.getPosition().x + 2 * Math.cos(barrel.getAngle())), (float) (barrel.getPosition().y + 2 * Math.sin(barrel.getAngle())), Properties.GlobalSizes.BULLET_RADIUS, true);
        float speed = 80;
        bullet.setLinearVelocity(new Vector2((float) (speed * Math.cos(barrel.getAngle())), (float) (speed * Math.sin(barrel.getAngle()))));
        bullet.getFixtureList().get(0).setUserData(new Bullet(21));
        bullet.getFixtureList().get(0).setSensor(true);
    }

    @Override
    public void aimTo(AimPoint aimPoint) {
        this.aimPoint = aimPoint;
        recalculateAim();
    }

    @Override
    public void recalculateAim() {
        setBarrelPosition();
    }

    @Override
    public void startFire() {
        super.startFire();
        System.out.println("STARTED FIRE");
        burn();
    }


    @Override
    public void burn() {
        Module eng = (Module) engine.getUserData();
        eng.setHp(eng.getHp() - 10);
        System.out.println("FIRE IN TANK " + eng.getHp());
    }

    @Override
    public void explode() {

    }

    //TODO FIX WHEELS

    @Override
    public float getX() {
        return base.getPosition().x;
    }

    @Override
    public float getY() {
        return base.getPosition().y;
    }

    @Override
    public float getAngle() {
        return base.getAngle();
    }

    //TODO remake aim calculation
    private void setBarrelPosition() {
        float angle = (float) (Math.atan((aimPoint.getY() - barrel.getPosition().y) / (aimPoint.getX() - barrel.getPosition().x)) * MathUtils.radiansToDegrees) + base.getAngle();
        if (angle < -15) {
            angle = -15;
        }
        if (angle > 15) {
            angle = 15;
        }

        barrelFixer.setLimits(angle * MathUtils.degreesToRadians, angle * MathUtils.degreesToRadians);

    }

}
