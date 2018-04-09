package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nickdnepr.panzermarch.MyGame;

public class MenuScreen extends AbstractScreen {

    SpriteBatch batch;
    Texture img;
    float deltaX = 20.0f;
    float x = 0.0f;

    public MenuScreen(MyGame game) {
        super(game);

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isTouched()){
            game.setScreen(new BoxAndStageScreen(game));
        }
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        deltaX += deltaX * Gdx.graphics.getDeltaTime();
        x += 10.0*Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(img, x, 0);
        batch.end();
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
        batch.dispose();
        img.dispose();
    }
}
