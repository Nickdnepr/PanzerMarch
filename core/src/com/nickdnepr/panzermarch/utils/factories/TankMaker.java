package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.nickdnepr.panzermarch.bodies.BlankTank;
import com.nickdnepr.panzermarch.bodies.LightTank;
import com.nickdnepr.panzermarch.bodies.Tank;
import com.nickdnepr.panzermarch.mechanics.Armor;
import com.nickdnepr.panzermarch.mechanics.Module;
import com.nickdnepr.panzermarch.utils.constants.TankTypes;


import static com.nickdnepr.panzermarch.utils.constants.Properties.TankProperties.*;

public class TankMaker {

    public static final int ORIENTATION_LEFT = 1;
    public static final int ORIENTATION_RIGHT = -1;

    public static Tank makeTank(int type, int model, World world, float x, float y, int or) {
        Tank tank = null;
        Body base = BodyMaker.createEmptyBody(world, x, y);
        //TODO REMAKE TANK CREATION
        switch (model) {
            case TankTypes.TankModels.TEST_LIGHT_TANK: {

                System.out.println(base.getAngle());
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.ROOF_AND_BOTTOM_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(0, 1))).setUserData(new Armor(TestLightTank.ARMOR, 0));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.ROOF_AND_BOTTOM_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(0, -1))).setUserData(new Armor(TestLightTank.ARMOR, 0));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.UPPER_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(3.8f * or, 0.5f), -30 * or)).setUserData(new Armor(TestLightTank.ARMOR, -30 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.UPPER_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(-3.8f * or, 0.5f), 30 * or)).setUserData(new Armor(TestLightTank.ARMOR, 30 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.LOWER_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(3.8f * or, -0.5f), 30 * or)).setUserData(new Armor(TestLightTank.ARMOR, 30 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.LOWER_PLATE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(-3.8f * or, -0.5f), -30 * or)).setUserData(new Armor(TestLightTank.ARMOR, -30 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.TURRET_SIDE_PALE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(0.5f * or, 1.6f), 60 * or)).setUserData(new Armor(TestLightTank.ARMOR + 40, 60 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.TURRET_SIDE_PALE_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(2.5f * or, 1.6f), -60 * or)).setUserData(new Armor(TestLightTank.ARMOR + 40, -60 * or));
                base.createFixture(FixtureMaker.createBoxFixture(TestLightTank.TURRET_ROOF_WIDTH, TestLightTank.PLATE_HEIGHT, new Vector2(1.5f * or, 2.2f))).setUserData(new Armor(TestLightTank.ARMOR, 0));


                Fixture engine = base.createFixture(FixtureMaker.createBoxFixture(3, 1.2f, new Vector2(-1.5f, 0)));
                engine.setUserData(new Module(Module.ENGINE, 5));

                Body wheel1 = BodyMaker.makeCircle(world, x - 3, y - 1, 0.7f);
                Body wheel2 = BodyMaker.makeCircle(world, x - 1, y + 1, 0.7f);
                Body wheel3 = BodyMaker.makeCircle(world, x + 1, y + 1, 0.7f);
                Body wheel4 = BodyMaker.makeCircle(world, x + 3, y - 1, 0.7f);

                WheelJoint wheel1Joint = JointsMaker.makeWheelJoint(world, new Vector2(-3, -1), base, wheel1);
                WheelJoint wheel2Joint = JointsMaker.makeWheelJoint(world, new Vector2(-1, -1), base, wheel2);
                WheelJoint wheel3Joint = JointsMaker.makeWheelJoint(world, new Vector2(1, -1), base, wheel3);
                WheelJoint wheel4Joint = JointsMaker.makeWheelJoint(world, new Vector2(3, -1), base, wheel4);

                Body barrel = BodyMaker.createBox(world, x + 4, y + 4, 0.4f, 0.4f);
                barrel.createFixture(FixtureMaker.createBoxFixture(2, 0.3f, new Vector2(1, 0)));
                RevoluteJoint barrelFixer = JointsMaker.makeRevoluteJoint(world, new Vector2(2.5f, 1.7f), new Vector2(0, 0), base, barrel, 0, 0);
                tank = new LightTank(type, model, base, wheel1, wheel2, wheel3, wheel4, wheel1Joint, wheel2Joint, wheel3Joint, wheel4Joint, barrel, barrelFixer, engine);

                break;
            }

            case TankTypes.TankModels.PANZER_1_LIGHT_TANK: {
                base.createFixture(FixtureMaker.createBoxFixture(Panzer1LightTank.BATTLE_BAY_ROOF_WIDTH, Panzer1LightTank.BATTLE_BAY_ROOF_HEIGHT, Panzer1LightTank.BATTLE_BAY_ROOF_POSITION)).setUserData(new Armor(Panzer1LightTank.BATTLE_BAY_ROOF_ARMOR, Panzer1LightTank.BATTLE_BAY_ROOF_ANGLE));
                base.createFixture(FixtureMaker.createBoxFixture(Panzer1LightTank.FRONT_TOP_WIDTH, Panzer1LightTank.FRONT_TOP_HEIGHT, Panzer1LightTank.FRONT_TOP_POSITION, Panzer1LightTank.FRONT_TOP_ANGLE)).setUserData(new Armor(Panzer1LightTank.FRONT_TOP_ARMOR, Panzer1LightTank.FRONT_TOP_ANGLE));
                base.createFixture(FixtureMaker.createBoxFixture(Panzer1LightTank.FRONT_MIDDLE_WIDTH, Panzer1LightTank.FRONT_MIDDLE_HEIGHT, Panzer1LightTank.FRONT_MIDDLE_POSITION, Panzer1LightTank.FRONT_MIDDLE_ANGLE)).setUserData(new Armor(Panzer1LightTank.FRONT_MIDDLE_ARMOR, Panzer1LightTank.FRONT_MIDDLE_ANGLE));
                tank = new BlankTank();
                break;
            }
        }
        return tank;
    }
}
