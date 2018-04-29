package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.nickdnepr.panzermarch.MyGame;
import com.nickdnepr.panzermarch.bodies.SimpleCar;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;
import com.nickdnepr.panzermarch.utils.factories.JointsMaker;
import com.nickdnepr.panzermarch.utils.physics.MContactListener;

public class TestBox2DScreen extends AbstractScreen {

    OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer renderer;
    Body body;
    Body circle;
    Body walls;
    SimpleCar car;


    public TestBox2DScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        initCamera();
        world = new World(new Vector2(0, -10), true);
        world.setContactListener(new MContactListener());
        renderer = new Box2DDebugRenderer();
        walls = BodyMaker.makeWalls(world, camera.viewportWidth, camera.viewportHeight);
//        body = BodyMaker.createSquare(world, 10, 60, 5);
        circle = BodyMaker.makeCircle(world, 10, 65, 3);
//        JointsMaker.makeWheelJoint(world, new Vector2(0, 0), body, circle);
//        JointsMaker.makeDistanceJoint(walls, body, world);
//        body = BodyMaker.createBox(world, 25, 20, 5, 0.5f);
        car = new SimpleCar(world, 20, 20, camera);
        SimpleCar car2 = new SimpleCar(world, 50, 20, camera);
//        JointsMaker.makeRevoluteJoint(world, new Vector2(1.5f, 2), new Vector2(-2, 0), car.getChasis(), body, 15, -5);
        Gdx.input.setInputProcessor(new InputMultiplexer(car.getInputAdapter()));
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        camera.update();
        renderer.render(world, camera.combined);
        world.step(delta, 10, 10);
        clearWorld();
        camera.position.set(car.getChasis().getPosition().x + 20, car.getChasis().getPosition().y + 30, 0);
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            car.applylinearForce(new Vector2(-100, 0));
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            car.applylinearForce(new Vector2(100, 0));
//        }

//        if (Gdx.input.isTouched()) {
//            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//            camera.unproject(mousePos);
//            if (mousePos.x > 70) {
//                car.applylinearForce(new Vector2(1000, 0));
//            }
//            if (mousePos.x < 30) {
//                car.applylinearForce(new Vector2(-1000, 0));
//            }
//        }
    }

    private void clearWorld() {
        Array<Fixture> fixtures = new Array<Fixture>();
        world.getFixtures(fixtures);
        for (Fixture fixture:fixtures){
            if (fixture.getUserData() instanceof Item &&((Item) fixture.getUserData()).isDead()){
                world.destroyBody(fixture.getBody());
            }
        }
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

    private void initCamera() {
        float CAMERA_WIDTH = 100;
        float CAMERA_HEIGHT = CAMERA_WIDTH * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        System.out.println(CAMERA_HEIGHT);
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(new Vector2(CAMERA_WIDTH / 2.0f, CAMERA_HEIGHT / 2.0f), 0);
    }

    private void createBody(Body body) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5, 5);
        fixtureDef.shape = shape;
        fixtureDef.density = 2;
        fixtureDef.restitution = 1;
        body.createFixture(fixtureDef);

    }
}
