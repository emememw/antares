package com.emveyh.antares.object;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameObjectManager {

	private static final GameObjectManager INSTANCE = new GameObjectManager();
	
	public static GameObjectManager getInstance() {
		return GameObjectManager.INSTANCE;
	}
	
	private GameObjectManager(){}
	
	private Map<String, GameObject> gameObjects = new HashMap<String, GameObject>();
	
	public void render(SpriteBatch batch) {
		for(Entry<String,GameObject> entry : gameObjects.entrySet()) {
			entry.getValue().draw(batch);
		}
	}
	
	public void addGameObject(GameObject gameObject) {
		gameObjects.put(gameObject.getTileX()+":"+gameObject.getTileY(), gameObject);
	}
	
	public GameObject getGameObjectAt(int tileX, int tileY) {
		GameObject result = gameObjects.get(tileX+":"+tileY);
		return result;
	}
	
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject.getTileX()+":"+gameObject.getTileY());
	}

	public void removeGameObjectAt(int tileX, int tileY) {
		gameObjects.remove(tileX+":"+tileY);
	}
	
}
