package com.emveyh.antares.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.emveyh.antares.core.GameState;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.entity.EntityManager;

public class InputManager {
	private static final InputManager INSTANCE = new InputManager();
	
	public static InputManager getInstance() {
		return InputManager.INSTANCE;
	}
	
	private InputManager() {}
	
	public void tick() {
		
		if(Gdx.input.justTouched()) {
			onTouch();
		}
		onKeyPressed();
		
	}
	
	private void onTouch() {
	}
	
	private void onKeyPressed() {
		if(GlobalConfig.getInstance().getCurrentGameState() == GameState.NORMAL) {
			checkPlayerMovement();
		}
	}

	private void checkPlayerMovement() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			EntityManager.getInstance().getPlayer().moveX(true);
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			EntityManager.getInstance().getPlayer().moveX(false);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			EntityManager.getInstance().getPlayer().moveY(true);
		} else if(Gdx.input.isKeyPressed(Keys.UP)) {
			EntityManager.getInstance().getPlayer().moveY(false);
		}
	}
	
	
}
