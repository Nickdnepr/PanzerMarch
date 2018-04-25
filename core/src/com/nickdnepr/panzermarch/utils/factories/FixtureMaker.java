package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class FixtureMaker {


    public static FixtureDef createBoxFixture(float width, float height, Vector2 center) {
        return createBoxFixture(width, height, center, 0);
    }

    public static FixtureDef createBoxFixture(float width, float height, Vector2 center, float angleInDegrees) {
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2, center, angleInDegrees * MathUtils.degreesToRadians);
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.restitution = 0.3f;
        return fixtureDef;
    }
}
