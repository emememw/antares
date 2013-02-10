package com.emveyh.antares.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.GlobalConfig;

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

}
