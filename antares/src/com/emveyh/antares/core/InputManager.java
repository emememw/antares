package com.emveyh.antares.core;

import com.badlogic.gdx.Gdx;
import com.emveyh.antares.entity.EntityManager;

public class InputManager {
	private static final InputManager INSTANCE = new InputManager();
	
	public static InputManager getInstance() {
		return InputManager.INSTANCE;
	}
	
	private InputManager() {}
	
	public void tick() {
		
		if(Gdx.input.justTouched()) {
			justTouchedEvent();
		}
		
	}
	
	private void justTouchedEvent() {
		int xTilePositionTouched = (int)(Gdx.input.getX())/GlobalConfig.FIXED_TILESIZE;
		int yTilePositionTouched = (int)GlobalConfig.getInstance().getViewHeight()/GlobalConfig.FIXED_TILESIZE-(int)(Gdx.input.getY())/GlobalConfig.FIXED_TILESIZE-1;
		
		EntityManager.getInstance().getPlayer().moveTo(xTilePositionTouched, yTilePositionTouched);
		
		
	}
	
}
