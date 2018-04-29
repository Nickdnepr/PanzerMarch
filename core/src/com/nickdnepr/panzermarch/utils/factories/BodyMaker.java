package com.nickdnepr.panzermarch.utils.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.nickdnepr.panzermarch.mechanics.Ground;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.utils.constants.ObjectTypes;

import java.util.Arrays;

public class BodyMaker {

    public static Body createSquare(World world, float x, float y, float size) {
        return createBox(world, x, y, size, size);
    }

    public static Body createEmptyBody(World world, float x, float y) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        return body;
    }

    public static Body createBox(World world, float x, float y, float width, float height) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        fixtureDef.shape = shape;
        fixtureDef.density = 5f;
        fixtureDef.restitution = 0.2f;
        body.createFixture(fixtureDef);
        return body;
    }

    public static Body makeWalls(World world, float width, float height) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(1, height - 1), new Vector2(1, 1), new Vector2(width - 1, 1), new Vector2(width - 1, height - 1), new Vector2(1, height - 1)});
        fixtureDef.shape = shape;
        fixtureDef.density = 2;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.8f;
        body.createFixture(fixtureDef).setUserData(new Ground());
        return body;
    }

    public static Body makeTestRelief(World world, int width, int minHeight, int maxHeight) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        ChainShape chainShape = new ChainShape();
        Vector2[] arr = new Vector2[width/5];
        int lastHeight = 1;
        for (int i = 0; i < width/5; i++) {
            int height = lastHeight+MathUtils.random(-2,2);
            if (height<minHeight){
                height=minHeight;
            }
            if (height>maxHeight){
                height=maxHeight;
            }
            arr[i] = new Vector2(i*5, height);
            lastHeight=height;
        }
        System.out.println(Arrays.toString(arr));
        chainShape.createChain(arr);
        //chainShape.createChain(new Vector2[]{new Vector2(5, 1), new Vector2(10, 3), new Vector2(15, 5), new Vector2(20, 8), new Vector2(25, 6), new Vector2(30, 3), new Vector2(35, 3), new Vector2(40, 2), new Vector2(45, 1)});
        fixtureDef.shape = chainShape;
        fixtureDef.density = 5;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0;
        body.createFixture(fixtureDef).setUserData(new Ground());
        return body;
    }

    public static Body makeCircle(World world, float x, float y, float radius) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape roundShape = new CircleShape();
        roundShape.setRadius(radius);
//        chainShape.createChain(new Vector2[]{new Vector2(5, 5), new Vector2(25, 75), new Vector2(500, 60), new Vector2(750, 150)});
        fixtureDef.shape = roundShape;
        fixtureDef.density = 5f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.friction = 3;

        body.createFixture(fixtureDef);
        return body;
    }


}
