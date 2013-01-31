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
		this.currentGameMap = generateNewGameMap();
	}
	
	

	public GameMap getCurrentGameMap() {
		return currentGameMap;
	}

	public void setCurrentGameMap(GameMap currentGameMap) {
		this.currentGameMap = currentGameMap;
	}
	
	
	public GameMap generateNewGameMap() {
		GameMap gameMap = new GameMap();
		gameMap.setTiles(new Tile[11][7]);
		for(int x = 0; x < gameMap.getTiles().length; x++) {
			for(int y = 0; y < gameMap.getTiles()[x].length; y++) {
				if(x == 5 && y == 0) {
					gameMap.getTiles()[x][y] = new Floor(x, y);
				} else 	if( x == 0 || y == 0 || x == gameMap.getTiles().length-1 || y == gameMap.getTiles()[x].length-1) {
					gameMap.getTiles()[x][y] = new Wall(x, y);
				
				} else {
					gameMap.getTiles()[x][y] = new Floor(x, y);
				}
			}
		}
		return gameMap;
	}
	
	
}
