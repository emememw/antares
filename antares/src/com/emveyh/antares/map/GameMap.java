package com.emveyh.antares.map;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.utils.Coord;

public class GameMap {

	private int width;
	private int height;
	private Tile[][] tiles;
	private WorldTileType worldTileType;
	private Tile[][] removeableTiles;

	public GameMap(int width, int height, WorldTileType worldTileType) {
		this.width = width;
		this.height = height;
		this.worldTileType = worldTileType;
		tiles = new Tile[width][height];
		removeableTiles = new Tile[width][height];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void render(SpriteBatch batch) {
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				if(removeableTiles[x][y] != null) {
					removeableTiles[x][y].render(batch, x * GlobalConfig.FIXED_TILESIZE, y * GlobalConfig.FIXED_TILESIZE);
				} else {
					tiles[x][y].render(batch, x * GlobalConfig.FIXED_TILESIZE, y * GlobalConfig.FIXED_TILESIZE);
				}
			}
		}
	}
	
	public List<Coord> getNonAccessibleTiles() {
		List<Coord> nonAccessibleTiles = new LinkedList<Coord>();
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				if(!tiles[x][y].isAccessible()) {
					nonAccessibleTiles.add(new Coord(x,y));
				}
			}
		}
		return nonAccessibleTiles;
	}

	public WorldTileType getWorldTileType() {
		return worldTileType;
	}

	public void setWorldTileType(WorldTileType worldTileType) {
		this.worldTileType = worldTileType;
	}

	public Tile[][] getRemoveableTiles() {
		return removeableTiles;
	}

	
	
	
	

}
