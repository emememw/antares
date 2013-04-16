package com.emveyh.antares.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
	private static final InputManager INSTANCE = new InputManager();
	
	public static InputManager getInstance() {
		return InputManager.INSTANCE;
	}
	
	private InputManager() {}

	private Map<Integer, InputState> inputMap = new HashMap<Integer, InputState>();

	@Override
	public boolean keyDown(int keycode) {
		InputState inputState = inputMap.get(keycode);
		if(inputState == null || inputState != InputState.RESETED) {
			inputMap.put(keycode, InputState.PRESSED);
		} 
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		inputMap.put(keycode, InputState.RELEASED);
		return false;
	}
	
	public boolean isKeyPressed(int keycode) {
		return inputMap.get(keycode) == InputState.PRESSED ? true : false;
	}
	
	public void resetKey(int keycode) {
		if(inputMap.get(keycode) == InputState.PRESSED) {
			inputMap.put(keycode, InputState.RESETED);
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
