package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.*;

public class JointsMaker {

    public static RopeJoint makeRopeJoint(World world, Body bodyA, Body bodyB, float length){
        RopeJointDef jointDef = new RopeJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.localAnchorA.set(new Vector2(0, 0));
        jointDef.localAnchorB.set(new Vector2(0, 0));
        jointDef.maxLength = length;
        return (RopeJoint) world.createJoint(jointDef);
    }

    public static DistanceJoint makeDistanceJoint(World world, Body bodyA, Body bodyB, float length) {

        DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.localAnchorA.set(new Vector2(0, 0));
        jointDef.localAnchorB.set(new Vector2(0, 0));
        jointDef.length = length;
        return (DistanceJoint) world.createJoint(jointDef);
    }

    public static WheelJoint makeWheelJoint(World world, Vector2 localAnchorBodyA, Body bodyA, Body bodyB) {
        WheelJointDef jointDef = new WheelJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.localAnchorA.set(localAnchorBodyA);
        jointDef.localAnchorB.set(0, 0);
        jointDef.localAxisA.set(Vector2.Y);
        jointDef.maxMotorTorque = 1000;
        return (WheelJoint) world.createJoint(jointDef);
    }

    public static RevoluteJoint makeRevoluteJoint(World world, Vector2 localAnchorBodyA, Vector2 localAnchorBodyB, Body bodyA, Body bodyB, float upperAngle, float lowerAngle) {
        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.enableLimit = true;
        jointDef.localAnchorA.set(localAnchorBodyA);
        jointDef.localAnchorB.set(localAnchorBodyB);
        jointDef.lowerAngle = MathUtils.degreesToRadians * lowerAngle;
        jointDef.upperAngle = MathUtils.degreesToRadians * upperAngle;
        return (RevoluteJoint) world.createJoint(jointDef);
    }
}
