package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nickdnepr.panzermarch.MyGame;
import com.nickdnepr.panzermarch.actors.MainHero;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;
import com.nickdnepr.panzermarch.utils.factories.TankMaker;
import com.nickdnepr.panzermarch.utils.input_controllers.UIController;

public class TestLevelScreen extends AbstractScreen {

    private Stage stage;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private MainHero mainHero;

    public TestLevelScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        initCamera();
        renderer = new Box2DDebugRenderer();
        stage = new Stage();
        BodyMaker.makeWalls(world, camera.viewportWidth, camera.viewportHeight);
        mainHero = new MainHero(TankMaker.makeTank("", "", world, 5, 5));
        Gdx.input.setInputProcessor(new InputMultiplexer(new UIController(camera, mainHero)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        camera.update();
        stage.act(delta);
        world.step(delta, 8, 8);
        renderer.render(world, camera.combined);
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
        float CAMERA_WIDTH = 20;
        float CAMERA_HEIGHT = CAMERA_WIDTH * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        System.out.println(CAMERA_HEIGHT);
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(new Vector2(CAMERA_WIDTH / 2.0f, CAMERA_HEIGHT / 2.0f), 0);
    }
}
