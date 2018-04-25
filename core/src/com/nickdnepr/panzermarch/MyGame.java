package com.nickdnepr.panzermarch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nickdnepr.panzermarch.screens.MenuScreen;
import com.nickdnepr.panzermarch.screens.TestBox2DScreen;
import com.nickdnepr.panzermarch.screens.TestLevelScreen;

public class MyGame extends Game {

    public static int WIDTH = 1024;
    public static int HEIGHT = 768;


    @Override
    public void create() {
        setScreen(new TestLevelScreen(this));
    }

}
