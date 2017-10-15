package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import scrollUpShooter.Constants;
import scrollUpShooter.SimpleScrollUpShooter;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int) Constants.SCREEN_WIDTH;
        config.height = (int) Constants.SCREEN_HEIGHT;
        new LwjglApplication(new SimpleScrollUpShooter(), config);
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = Const.APP_WIDTH;
//		config.height = Const.APP_HEIGHT;
//		new LwjglApplication(new MyGdxGame(), config);
    }
}
