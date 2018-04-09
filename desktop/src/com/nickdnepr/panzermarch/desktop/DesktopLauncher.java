package com.nickdnepr.panzermarch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nickdnepr.panzermarch.MyGame;

public class DesktopLauncher {


    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = MyGame.WIDTH;
        config.height = MyGame.HEIGHT;
        new LwjglApplication(new MyGame(), config);
    }
}
