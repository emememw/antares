package com.emveyh.antares.map;

import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.entity.EntityManager;
import com.emveyh.antares.utils.Coord;

public class MapManager {

	private static final MapManager INSTANCE = new MapManager();

	public static MapManager getInstance() {
		return MapManager.INSTANCE;
	}

	private MapManager() {
	}

	private GameMap world;
	private Coord currentWorldPosition;
	
	public void loadWorld() {
		this.world = MapGenerator.generateGameMap();
		outer: for(int x = 0; x < world.getTiles().length; x++) {
			for(int y = 0; y < world.getTiles()[x].length; y++) {
				if(world.getTiles()[x][y] == Tile.GRASS) {
					currentWorldPosition = new Coord(x,y);
					EntityManager.getInstance().getPlayer().setPosition(x*GlobalConfig.FIXED_TILESIZE, y*GlobalConfig.FIXED_TILESIZE);
					break outer;
				}
			}
		}
	}


	public GameMap getWorld() {
		return world;
	}

	public Coord getCurrentWorldPosition() {
		return currentWorldPosition;
	}

	public void setCurrentWorldPosition(Coord currentWorldPosition) {
		this.currentWorldPosition = currentWorldPosition;
	}
	
	public GameMap getCurrentMap() {
		return world;
	}
	
	
	
}
