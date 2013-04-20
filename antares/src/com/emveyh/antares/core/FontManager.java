package com.emveyh.antares.core;

import java.awt.Font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {

	private static final FontManager INSTANCE = new FontManager();
	
	public static FontManager getInstance() {
		return FontManager.INSTANCE;
	}
	
	private FontManager() {}
	
	private BitmapFont font;
	
	public void initialize() {
		font = new BitmapFont(Gdx.files.internal("data/ps2.fnt"), Gdx.files.internal("data/ps2.png"), false);
		font.setColor(Color.WHITE);
	}

	public BitmapFont getFont() {
		return font;
	}
	
	//new BitmapFont(Gdx.files.internal("data/ps2_border.fnt"), Gdx.files.internal("data/ps2_border.png"), false)
	
}
