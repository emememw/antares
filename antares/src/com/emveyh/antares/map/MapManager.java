package com.emveyh.antares.map;

public class MapManager {

	private static final MapManager INSTANCE = new MapManager();

	public static MapManager getInstance() {
		return MapManager.INSTANCE;
	}

	private MapManager() {
	}

	private GameMap gameMap;

	public void loadTestMap() {

		this.gameMap = new GameMap(13, 10);
		for (int x = 0; x < this.gameMap.getWidth(); x++) {
			for (int y = 0; y < this.gameMap.getHeight(); y++) {
				this.gameMap.getTiles()[x][y] = Tile.TEST;
			}
		}

	}

	public GameMap getGameMap() {
		return gameMap;
	}
	
	

}
