package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class FixtureMaker {


    public static FixtureDef createBoxFixture(float width, float height, Vector2 center) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2, center, 0);
        fixtureDef.shape = shape;
        return fixtureDef;
    }
}
