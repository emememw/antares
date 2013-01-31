package com.emveyh.antares.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public class GameMap {
	private Tile[][] tiles;
	
	public GameMap() {
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void render(SpriteBatch batch) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				batch.draw(tiles[x][y].getTexture(), x*GlobalConfig.FIXED_TILESIZE,y*GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE);
				if(tiles[x][y].getEntity() != null) {
					
				}
			}
		}
	}
	
}
