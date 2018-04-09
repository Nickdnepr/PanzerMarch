package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.nickdnepr.panzermarch.MyGame;

public class Box2DScreen extends AbstractScreen {

    World world;
    OrthographicCamera camera;
    Body body;
    Body wall;
    Box2DDebugRenderer renderer;

    public Box2DScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        camera.position.set(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20, 0);
        renderer = new Box2DDebugRenderer();
        createBody(body);
//        createWalls(wall);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        renderer.render(world, camera.combined);
        camera.update();
        world.step(delta, 4, 4);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void createBody(Body body) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, 50);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 10);
        fixtureDef.shape = shape;
        fixtureDef.density = 2;
        fixtureDef.restitution = 1;
        body.createFixture(fixtureDef);

    }

    private void createWalls(Body walls, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(5, height - 5), new Vector2(5, 5), new Vector2(width - 5, 5), new Vector2(width - 5, height - 5)});
        fixtureDef.shape = shape;
        fixtureDef.density = 2;
        body.createFixture(fixtureDef);
    }
}
