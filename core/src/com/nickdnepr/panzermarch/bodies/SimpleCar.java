package com.nickdnepr.panzermarch.bodies;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nickdnepr.panzermarch.mechanics.Armor;
import com.nickdnepr.panzermarch.mechanics.Bullet;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.utils.constants.ObjectTypes;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;
import com.nickdnepr.panzermarch.utils.factories.FixtureMaker;
import com.nickdnepr.panzermarch.utils.factories.JointsMaker;

public class SimpleCar extends Actor {

    private Body chasis;
    private Body breech;
    private Body leftWheel;
    private Body rightWheel;
    private WheelJoint leftJoint;
    private WheelJoint rightJoint;
    private RevoluteJoint breechFixer;
    private World world;
    private OrthographicCamera camera;

    public SimpleCar(World world, float x, float y, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;
        chasis = BodyMaker.createBox(world, x, y, 8, 2);
        chasis.getFixtureList().get(0).setUserData(new Armor(1000, 0));
        chasis.createFixture(FixtureMaker.createBoxFixture(0.5f, 0.75f, new Vector2(2, 1.25f))).setUserData(new Armor(10, 90));
        chasis.createFixture(FixtureMaker.createBoxFixture(0.5f, 0.75f, new Vector2(2, 2.75f))).setUserData(new Armor(10, 90));
        chasis.createFixture(FixtureMaker.createBoxFixture(0.5f, 2, new Vector2(-2, 2), -45)).setUserData(new Armor(10, -45));
        chasis.createFixture(FixtureMaker.createBoxFixture(4, 0.5f, new Vector2(0, 3))).setUserData(new Armor(10, 0));
//        turret = BodyMaker.createBox(world, x, y+0.75f, 2.5f, 1.5f);
        leftWheel = BodyMaker.makeCircle(world, x, y, 1);
        rightWheel = BodyMaker.makeCircle(world, x, y, 1);
        leftJoint = JointsMaker.makeWheelJoint(world, new Vector2(-3, -1.2f), chasis, leftWheel);
        rightJoint = JointsMaker.makeWheelJoint(world, new Vector2(3, -1.2f), chasis, rightWheel);
//        turreltFixer = JointsMaker.makeDistanceJoint(world, chasis, turret, 1.6f);

        breech = BodyMaker.createBox(world, x + 2, y + 2, 1, 1);
        breechFixer = JointsMaker.makeRevoluteJoint(world, new Vector2(1.75f, 2), new Vector2(-0.75f, 0), chasis, breech, 15, -15);
        breech.createFixture(FixtureMaker.createBoxFixture(3, 0.5f, new Vector2(1.5f, 0)));
//        barrel = BodyMaker.createBox(world, x + 3, y + 2, 4, 0.5f);
//        barrelFixer = JointsMaker.makeRopeJoint(world, breech, barrel, 0.5f);


    }

    public void applylinearForce(Vector2 force) {
        chasis.applyForce(force, new Vector2(0, 0), true);
        System.out.println(leftWheel.getAngle() * MathUtils.radiansToDegrees);
    }

    public Body getChasis() {
        return chasis;
    }

    public CarInputAdapter getInputAdapter() {
        return new CarInputAdapter(this);
    }

    private void shoot() {
        Body bullet = BodyMaker.makeCircle(world, breech.getPosition().x + 4, (float) (breech.getPosition().y + breech.getPosition().y * Math.sin(breech.getAngle())), 0.5f);
        float speed = 200;
        bullet.setLinearVelocity(new Vector2((float) (speed * Math.cos(breech.getAngle())), (float) (speed * Math.sin(breech.getAngle()))));
        bullet.getFixtureList().get(0).setUserData(new Bullet(15));
        bullet.getFixtureList().get(0).setSensor(true);
        bullet.setBullet(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private class CarInputAdapter extends InputAdapter {

        private SimpleCar simpleCar;

        public CarInputAdapter(SimpleCar simpleCar) {
            this.simpleCar = simpleCar;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            setBarrelPosition(screenX, screenY);
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            breechFixer.setLimits(-15 * MathUtils.degreesToRadians, 15 * MathUtils.degreesToRadians);
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            setBarrelPosition(screenX, screenY);
            return true;
        }

        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.LEFT: {
                    simpleCar.leftJoint.enableMotor(true);
                    simpleCar.leftJoint.setMotorSpeed(1200);
                    break;
                }
                case Input.Keys.RIGHT: {
                    simpleCar.leftJoint.setMotorSpeed(-1200);
                    simpleCar.leftJoint.enableMotor(true);
                    break;
                }
                case Input.Keys.SPACE: {
                    shoot();
                }
            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            switch (keycode) {
                case Input.Keys.LEFT: {
                    simpleCar.leftJoint.enableMotor(false);
                    break;
                }
                case Input.Keys.RIGHT: {
                    simpleCar.leftJoint.enableMotor(false);
                    break;
                }
            }
            return true;
        }

        private void setBarrelPosition(int screenX, int screenY) {
            Vector3 pos = new Vector3(screenX, screenY, 0);
            camera.unproject(pos);
//            System.out.println(Math.atan((pos.y - breech.getPosition().y) / (pos.x - breech.getPosition().x)) * MathUtils.radiansToDegrees);
            float angle = (float) (Math.atan((pos.y - breech.getPosition().y) / (pos.x - breech.getPosition().x)) * MathUtils.radiansToDegrees);
            if (angle < -15) {
                angle = -15;
            }
            if (angle > 15) {
                angle = 15;
            }
            breechFixer.setLimits(angle * MathUtils.degreesToRadians, angle * MathUtils.degreesToRadians);

        }
    }
}
