package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nickdnepr.panzermarch.MyGame;
import com.nickdnepr.panzermarch.actors.MainHero;
import com.nickdnepr.panzermarch.actors.ui.Button;
import com.nickdnepr.panzermarch.bodies.SimpleCar;
import com.nickdnepr.panzermarch.mechanics.Armor;
import com.nickdnepr.panzermarch.mechanics.Item;
import com.nickdnepr.panzermarch.utils.config.GameConfig;
import com.nickdnepr.panzermarch.utils.constants.ButtonTags;
import com.nickdnepr.panzermarch.utils.constants.TankTypes;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;
import com.nickdnepr.panzermarch.utils.factories.FixtureMaker;
import com.nickdnepr.panzermarch.utils.factories.TankMaker;
import com.nickdnepr.panzermarch.utils.input_controllers.UIController;
import com.nickdnepr.panzermarch.utils.math.AngleUtil;
import com.nickdnepr.panzermarch.utils.physics.MContactListener;

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
        System.out.println(AngleUtil.convertAngle(0));
        world = new World(new Vector2(0, -10), true);
        initCamera();
        renderer = new Box2DDebugRenderer();

        int width = 5000;

        stage = new Stage(new FitViewport(camera.viewportWidth, camera.viewportHeight, camera));
        BodyMaker.makeWalls(world, width, camera.viewportHeight);
        System.out.println(camera.viewportWidth + " " + camera.viewportHeight);
        //BodyMaker.makeTestRelief(world, width, 1, 10);
        mainHero = new MainHero(TankMaker.makeTank(TankTypes.LIGHT_TANK, TankTypes.TankModels.TEST_LIGHT_TANK, world, 8, 15));
        stage.addActor(mainHero);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, new UIController(camera, mainHero)));
        world.setContactListener(new MContactListener());
        stage.setDebugAll(true);
        GameConfig.pixelsPerMeter = Gdx.graphics.getWidth() / camera.viewportWidth;
        initUI(mainHero);
        System.out.println(stage.getActors().size);
        Body b = BodyMaker.createEmptyBody(world, 20, 10);
        b.createFixture(FixtureMaker.createBoxFixture(5,1,new Vector2(0,0),90)).setUserData(new Armor(10,90));
        b.createFixture(FixtureMaker.createBoxFixture(7,1,new Vector2(5,1),-45)).setUserData(new Armor(10,-45));
        b.createFixture(FixtureMaker.createBoxFixture(5,1,new Vector2(10,0),-30)).setUserData(new Armor(10,-30));
        b.createFixture(FixtureMaker.createBoxFixture(5,1,new Vector2(20,0),90)).setUserData(new Armor(10,0));

        //SimpleCar car = new SimpleCar(world, 20, 10, camera);
        //MainHero test = new MainHero(TankMaker.makeTank(TankTypes.LIGHT_TANK, TankTypes.TankModels.TEST_LIGHT_TANK, world, 30,15));
    }

    private void initUI(MainHero mainHero) {
        switch (mainHero.getTank().getType()) {
            case TankTypes.LIGHT_TANK: {
                stage.addActor(new Button(ButtonTags.DRIVE_LEFT, camera, mainHero));
                stage.addActor(new Button(ButtonTags.DRIVE_RIGHT, camera, mainHero));
                stage.addActor(new Button(ButtonTags.SHOOT, camera, mainHero));

            }
        }


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        camera.position.set(mainHero.getTank().getX() + 20, mainHero.getTank().getY() + 15, 0);
        camera.update();
        stage.act(delta);
        world.step(delta, 10, 20);
        renderer.render(world, camera.combined);
        stage.draw();
        clearWorld();
    }

    private void clearWorld() {
        Array<Fixture> fixtures = new Array<Fixture>();
        world.getFixtures(fixtures);
        for (Fixture fixture : fixtures) {
            if (fixture.getUserData() instanceof Item && ((Item) fixture.getUserData()).isDead()) {
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
        float CAMERA_WIDTH = 80;
        float CAMERA_HEIGHT = CAMERA_WIDTH * ((float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        System.out.println(CAMERA_HEIGHT);
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        camera.position.set(new Vector2(CAMERA_WIDTH / 2.0f, CAMERA_HEIGHT / 2.0f), 0);
    }
}
