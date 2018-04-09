package com.nickdnepr.panzermarch.screens;

import com.badlogic.gdx.Screen;
import com.nickdnepr.panzermarch.MyGame;

public abstract class AbstractScreen implements Screen{

    protected MyGame game;

    public AbstractScreen(MyGame game) {
        this.game = game;
    }
}
