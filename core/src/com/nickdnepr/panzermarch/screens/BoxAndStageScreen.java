package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nickdnepr.panzermarch.MyGame;
import com.nickdnepr.panzermarch.actors.SquareActor;
import com.nickdnepr.panzermarch.utils.factories.BodyMaker;

public class BoxAndStageScreen extends AbstractScreen {
    private Stage stage;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private SquareActor actor;

    public BoxAndStageScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        world = new World(new Vector2(0, -800), true);
        renderer = new Box2DDebugRenderer();
        actor = new SquareActor(BodyMaker.createSquare(world, 50, 150, 150));
        stage.addActor(actor);
        BodyMaker.makeWalls(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.setDebugAll(true);
        BodyMaker.makeTestRelief(world, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        stage.act();
        stage.draw();
        renderer.render(world, camera.combined);
        camera.update();
        world.step(delta, 4, 4);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            actor.getBody().applyForce(-50000000, 0, actor.getX() + 50, actor.getY() + 50, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            actor.getBody().applyForce(50000000, 0, actor.getX() + 50, actor.getY() + 50, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            actor.getBody().applyForce(0, 50000000, actor.getX() + 50, actor.getY() + 50, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            actor.getBody().applyForce(0, -5000000, actor.getX() + 50, actor.getY() + 50, true);
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
}
