package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.nickdnepr.panzermarch.bodies.LightTank;
import com.nickdnepr.panzermarch.bodies.Tank;

public class TankMaker {

    public static Tank makeTank(String type, String model, World world, float x, float y) {
        Tank tank;

        Body base = BodyMaker.createEmptyBody(world, x, y);
        base.createFixture(FixtureMaker.createBoxFixture(6, 0.2f, new Vector2(0, 1)));
        base.createFixture(FixtureMaker.createBoxFixture(6, 0.2f, new Vector2(0, -1)));
        base.createFixture(FixtureMaker.createBoxFixture(2, 0.2f, new Vector2(3.8f, 0.5f), -30));
        base.createFixture(FixtureMaker.createBoxFixture(2, 0.2f, new Vector2(-3.8f, 0.5f), 30));
        base.createFixture(FixtureMaker.createBoxFixture(2, 0.2f, new Vector2(3.8f, -0.5f), 30));
        base.createFixture(FixtureMaker.createBoxFixture(2, 0.2f, new Vector2(-3.8f, -0.5f), -30));

        Body wheel1 = BodyMaker.makeCircle(world, x, y, 0.7f);
        Body wheel2 = BodyMaker.makeCircle(world, x, y, 0.7f);
        Body wheel3 = BodyMaker.makeCircle(world, x, y, 0.7f);
        Body wheel4 = BodyMaker.makeCircle(world, x, y, 0.7f);

        WheelJoint wheel1Joint = JointsMaker.makeWheelJoint(world, new Vector2(-3, -1), base, wheel1);
        WheelJoint wheel2Joint = JointsMaker.makeWheelJoint(world, new Vector2(-1, -1), base, wheel2);
        WheelJoint wheel3Joint = JointsMaker.makeWheelJoint(world, new Vector2(1, -1), base, wheel3);
        WheelJoint wheel4Joint = JointsMaker.makeWheelJoint(world, new Vector2(3, -1), base, wheel4);
        tank = new LightTank(type, model, base, wheel1, wheel2, wheel3, wheel4, wheel1Joint, wheel2Joint, wheel3Joint, wheel4Joint);

        return tank;
    }
}
