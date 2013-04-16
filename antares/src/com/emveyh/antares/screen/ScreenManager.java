package com.emveyh.antares.screen;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {

	private static final ScreenManager INSTANCE = new ScreenManager();
	
	public static ScreenManager getInstance() {
		return ScreenManager.INSTANCE;
	}
	
	private ScreenManager(){}
	
	private Map<String, Screen> screenMap = new HashMap<String, Screen>();
	
	private Screen currentScreen;
	
	public void initialize() {
		registerScreen(ScreenIdentifier.WORLD_SCREEN, new WorldScreen());
		showScreen(ScreenIdentifier.WORLD_SCREEN);
	}
	
	private void registerScreen(ScreenIdentifier identifier, Screen screen) {
		screenMap.put(identifier.toString(), screen);
	}
	
	public void showScreen(ScreenIdentifier identifier) {
		currentScreen = screenMap.get(identifier.toString());
	}
	
	public void render(SpriteBatch batch) {
		currentScreen.render(batch);
	}
	
	
}
