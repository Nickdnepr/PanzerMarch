package com.nickdnepr.panzermarch.utils.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.nickdnepr.panzermarch.bodies.Tank;
import com.nickdnepr.panzermarch.mechanics.Armor;
import com.nickdnepr.panzermarch.mechanics.Bullet;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.mechanics.Module;
import com.nickdnepr.panzermarch.utils.constants.ObjectTypes;
import com.nickdnepr.panzermarch.utils.math.AngleUtil;

public class MContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Item itemA;
        Item itemB;

        if (fixtureA.getUserData() != null) {
            itemA = (Item) fixtureA.getUserData();
        } else {
            return;
        }
        if (fixtureB.getUserData() != null) {
            itemB = (Item) fixtureB.getUserData();
        } else {
            return;
        }


        Fixture bulletFixture;
        Bullet bullet;

        Fixture anotherFixture;
        Item anotherItem;

        if (itemA.getType() == Item.BULLET) {
            bullet = (Bullet) itemA;
            bulletFixture = fixtureA;
            anotherItem = itemB;
            anotherFixture = fixtureB;
        } else {
            if (itemB.getType() == Item.BULLET) {
                bullet = (Bullet) itemB;
                bulletFixture = fixtureB;
                anotherItem = itemA;
                anotherFixture = fixtureA;
            } else {
                return;
            }
        }

        if (anotherItem.getType() == Item.GROUND) {
            bullet.markAsDead();
        }

        if (anotherItem.getType() == Item.ARMOR) {
            Armor armor = (Armor) anotherItem;
            int arm = armor.getArmor();
            int ap = bullet.getArmorPiercing();
            System.out.println("STARTED COLLISION, BASE AP IS " + ap + " BASE ARM IS " + arm);
            float armAngle = AngleUtil.convertAngle(armor.getAngle() + anotherFixture.getBody().getAngle() * MathUtils.radiansToDegrees);
            //TODO fix possible divideByZero, remake calculation
            float bulletAngle = AngleUtil.convertAngle((float) Math.atan(bulletFixture.getBody().getLinearVelocity().y / Math.abs(bulletFixture.getBody().getLinearVelocity().x)) * MathUtils.radiansToDegrees);
            float meetingAngle;
            if (bulletFixture.getBody().getLinearVelocity().x > 0) {
                //LEFT COLLIDING
                System.out.println("LEFT COLLIDING, " + armAngle + " " + bulletAngle);
                if (armAngle * bulletAngle > 0) {
                    meetingAngle = Math.abs(armAngle) - Math.abs(bulletAngle);
                } else {
                    meetingAngle = 180 - Math.abs(armAngle) - Math.abs(bulletAngle);
                }

            } else {
                //RIGHT COLLIDING
                if (armAngle * bulletAngle < 0) {
                    meetingAngle = Math.abs(armAngle) - Math.abs(bulletAngle);
                } else {
                    meetingAngle = 180 - Math.abs(armAngle) - Math.abs(bulletAngle);

                }
            }
            meetingAngle = AngleUtil.convertAngle(meetingAngle);
            System.out.println("CONVERTED MEETING ANGLE IS " + meetingAngle + " SIN IS " + Math.sin(meetingAngle * MathUtils.degreesToRadians));
            arm = (int) Math.round((arm / (Math.sin(meetingAngle * MathUtils.degreesToRadians))));

            System.out.println("HIT ANGLE IS " + AngleUtil.convertAngle(meetingAngle));
            System.out.println("BULLET ANGLE IS " + bulletAngle);

            if (ap > arm) {
                System.out.println("ARMOR PIERCED, ARM IS " + arm);
                bullet.setArmorPiercing(ap - arm);
                System.out.println("LEFT AP IS " + bullet.getArmorPiercing());
            } else {

                //TODO make rikoshets
                bullet.markAsDead();
                //if (meetingAngle < 35) {
                //     System.out.println("RIKOSHET");
                //bulletFixture.setSensor(false);
                //} else {
                //    System.out.println("ARMOR NOT PIERCED");

                // }
            }

            System.out.println("----------------");
            //System.out.println(bulletFixture.getBody().linVelLoc.y / bulletFixture.getBody().linVelLoc.x+" "+bulletFixture.getBody().getLinearVelocity().y +" "+ bulletFixture.getBody().getLinearVelocity().x);

        }

        if (anotherItem.getType() == Item.MODULE) {
            Module module = (Module) anotherItem;
            switch (module.getModule()) {
                case Module.ENGINE: {
                    if (module.getArmor() < bullet.getArmorPiercing()) {
                        bullet.setArmorPiercing(bullet.getArmorPiercing()-module.getArmor());
                        module.setHp(module.getHp() - bullet.getArmorPiercing());
                    }else {
                        bullet.markAsDead();
                    }
                    System.out.println("ENGINE HIT");
                    System.out.println("HP IS "+module.getHp());

                    if (module.isBurnable()){
                        int chance = MathUtils.random(0,9);
                        if (chance<10){
                            Tank tank = (Tank) anotherFixture.getBody().getUserData();
                            System.out.println("Tank is"+tank);
                            tank.startFire();
                            System.out.println("FIRE");
                        }
                    }
                    System.out.println("----------------");
                    break;
                }
            }
        }

    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
