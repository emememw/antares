package com.emveyh.antares.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class GameObjectManager {

	private static final GameObjectManager INSTANCE = new GameObjectManager();
	
	public static GameObjectManager getInstance() {
		return GameObjectManager.INSTANCE;
	}
	
	private GameObjectManager(){}
	
	private Array<GameObject> gameObjects = new Array<GameObject>();

	public Array<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void render(SpriteBatch batch) {
		for(GameObject object : gameObjects) {
			object.draw(batch);
		}
	}
	
	
}
