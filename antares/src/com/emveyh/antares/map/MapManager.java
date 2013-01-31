package com.emveyh.antares.map;


public class MapManager {

	private static final MapManager INSTANCE = new MapManager();
	
	public static MapManager getInstance() {
		return MapManager.INSTANCE;
	}
	
	private MapManager() {
		generateInitialGameMap();
	}
	
	private GameMap currentGameMap;
	
	private void generateInitialGameMap() {
		this.currentGameMap = new GameMap();
	}
	
	

	public GameMap getCurrentGameMap() {
		return currentGameMap;
	}

	public void setCurrentGameMap(GameMap currentGameMap) {
		this.currentGameMap = currentGameMap;
	}
	
	
	
}
