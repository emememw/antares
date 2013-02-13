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

		/*this.gameMap = new GameMap(130, 100);
		for (int x = 0; x < this.gameMap.getWidth(); x++) {
			for (int y = 0; y < this.gameMap.getHeight(); y++) {
				if(x == 5 && y == 5) {
					this.gameMap.getTiles()[x][y] = Tile.WALL;
				} else if(x == 7 && y == 5) {
					this.gameMap.getTiles()[x][y] = Tile.WALL;
				} else if(x == 5 && y == 7) {
					this.gameMap.getTiles()[x][y] = Tile.WALL;
				} else if(x == 7 && y == 7) {
					this.gameMap.getTiles()[x][y] = Tile.WALL;
				} 
				else {
					this.gameMap.getTiles()[x][y] = Tile.FLOOR;
				}
			}
		}*/
		this.gameMap = new TestGameMap();

	}

	public GameMap getGameMap() {
		return gameMap;
	}
	
	

}
