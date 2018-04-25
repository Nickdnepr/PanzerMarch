package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nickdnepr.panzermarch.MyGame;
import com.nickdnepr.panzermarch.actors.MainHero;

import java.util.Random;

public class MainGameScreen extends AbstractScreen {

    OrthographicCamera camera;
    Stage stage;
    MainHero mainHero;
    Random random;

    public MainGameScreen(MyGame game) {
        super(game);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        //mainHero = new MainHero();
        mainHero.setPosition(0, 0);
        stage.addActor(mainHero);
        random = new Random();
//        mainHero.addAction(Actions.moveTo(50,50,5));
        Gdx.input.setInputProcessor(stage);
        System.out.println(Gdx.graphics.getWidth() + "   " + Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
//        System.out.println(delta);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
//        System.out.println(mainHero.getX() + "   " + mainHero.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            mainHero.setPosition(mainHero.getX() + 15 * delta, mainHero.getY());
            mainHero.addAction(Actions.moveTo(random.nextInt(800), random.nextInt(600), 5));
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
