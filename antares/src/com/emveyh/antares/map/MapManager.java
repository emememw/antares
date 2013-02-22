package com.emveyh.antares.map;

import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.entity.EntityManager;
import com.emveyh.antares.utils.Coord;
import com.emveyh.antares.utils.Direction;

public class MapManager {

	private static final MapManager INSTANCE = new MapManager();

	public static MapManager getInstance() {
		return MapManager.INSTANCE;
	}

	private MapManager() {
	}

	private GameMap[][] world;
	private Coord currentWorldPosition;
	
	public void loadWorld() {
		this.world = MapGenerator.generateWorld();
		outer: for(int x = 0; x < world.length; x++) {
			for(int y = 0; y < world[x].length; y++) {
				if(world[x][y].getWorldTileType() == WorldTileType.LAND) {
					currentWorldPosition = new Coord(x,y);
					break outer;
				}
			}
		}
	}


	public GameMap[][] getWorld() {
		return world;
	}

	public Coord getCurrentWorldPosition() {
		return currentWorldPosition;
	}

	public void setCurrentWorldPosition(Coord currentWorldPosition) {
		this.currentWorldPosition = currentWorldPosition;
	}
	
	public GameMap getCurrentMap() {
		return world[currentWorldPosition.getX()][currentWorldPosition.getY()];
	}
	
	public void loadNextGameMap(Direction direction) {
		if(direction == Direction.RIGHT && currentWorldPosition.getX()+1 < world.length-1) {
			currentWorldPosition.setX(currentWorldPosition.getX()+1);
			EntityManager.getInstance().getPlayer().setX(0);
		} else if(direction == Direction.LEFT && currentWorldPosition.getX()-1 >= 0) {
			currentWorldPosition.setX(currentWorldPosition.getX()-1);
			EntityManager.getInstance().getPlayer().setX((getCurrentMap().getWidth()-1)*GlobalConfig.FIXED_TILESIZE);
		} else if(direction == Direction.UP && currentWorldPosition.getY()+1 < world[0].length-1) {
			currentWorldPosition.setY(currentWorldPosition.getY()+1);
			EntityManager.getInstance().getPlayer().setY(0);
		} else if(direction == Direction.DOWN && currentWorldPosition.getY()-1 >= 0) {
			currentWorldPosition.setY(currentWorldPosition.getY()-1);
			EntityManager.getInstance().getPlayer().setY((getCurrentMap().getHeight()-1)*GlobalConfig.FIXED_TILESIZE);
		}
	}
	
}
