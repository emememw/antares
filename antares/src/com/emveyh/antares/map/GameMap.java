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

	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
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
				tiles[x][y].render(batch, x * GlobalConfig.FIXED_TILESIZE, y * GlobalConfig.FIXED_TILESIZE);
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

}
