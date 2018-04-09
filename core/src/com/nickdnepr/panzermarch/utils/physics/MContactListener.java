package com.nickdnepr.panzermarch.utils.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.nickdnepr.panzermarch.mechanics.Armor;
import com.nickdnepr.panzermarch.mechanics.Bullet;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.utils.constants.ObjectTypes;

public class MContactListener implements ContactListener {

    private World world;

    public MContactListener(World world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() != null && fixtureB.getUserData() != null && fixtureA.getUserData() instanceof Item && fixtureB.getUserData() instanceof Item) {
            Item itemA = (Item) fixtureA.getUserData();
            Item itemB = (Item) fixtureB.getUserData();
            if ((itemA.getType().equals(ObjectTypes.BULLET) && itemB.getType().equals(ObjectTypes.GROUND))) {
                itemA.markAsDead();
            } else {
                if ((itemB.getType().equals(ObjectTypes.BULLET) && itemA.getType().equals(ObjectTypes.GROUND))) {
                    itemB.markAsDead();
                }
            }

            if (itemA instanceof Bullet && itemB instanceof Armor) {
                System.out.println("hit");
                System.out.println("AP is " + ((Bullet) itemA).getArmorPiercing()+" Armor is "+((Armor) itemB).getArmor());
                if (((Bullet) itemA).getArmorPiercing() < ((Armor) itemB).getArmor()) {
//                    fixtureA.setSensor(false);
                    itemA.markAsDead();
                    System.out.println("armor not pierced");
                }
            } else {

                if (itemB instanceof Bullet && itemA instanceof Armor) {
                    System.out.println("hit");
                    System.out.println("AP is " + ((Bullet) itemB).getArmorPiercing()+" Armor is "+((Armor) itemA).getArmor());
                    if (((Bullet) itemB).getArmorPiercing() < ((Armor) itemA).getArmor()) {
//                        fixtureB.setSensor(false);
                        itemB.markAsDead();
                        System.out.println("armor not pierced");
                    }
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
