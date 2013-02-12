package com.emveyh.antares;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.emveyh.antares.core.GdxGame;

public class Main {
	public static void main(String[] args) {
		//test
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "antares";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 480;
		cfg.resizable = true;

		boolean fullscreen = false;

		if (fullscreen) {
			DisplayMode desktopDisplayMode = null;
			try {

				desktopDisplayMode = cfg.getDesktopDisplayMode();
				if (desktopDisplayMode != null) {
					cfg.width = desktopDisplayMode.width;
					cfg.height = desktopDisplayMode.height;
				}

			} catch (Exception ex) {
			}
		}
		
		new LwjglApplication(new GdxGame(), cfg);
	}
}
