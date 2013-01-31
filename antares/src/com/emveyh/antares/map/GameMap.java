package com.emveyh.antares.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public class GameMap {

	//test
	
	private Tile[][] tiles;
	
	public GameMap() {
		
		tiles = new Tile[11][7];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				if(x == 5 && y == 0) {
					tiles[x][y] = new Floor(x, y);
				} else 	if( x == 0 || y == 0 || x == tiles.length-1 || y == tiles[x].length-1) {
					tiles[x][y] = new Wall(x, y);
				
				} else {
					tiles[x][y] = new Floor(x, y);
				}
				
			}
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void renderMap(SpriteBatch batch) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				batch.draw(tiles[x][y].getTexture(), x*GlobalConfig.FIXED_TILESIZE,y*GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE);
				if(tiles[x][y].getEntity() != null) {
					batch.draw(TextureManager.getInstance().getSprites()[2][0], x*GlobalConfig.FIXED_TILESIZE,y*GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE,GlobalConfig.FIXED_TILESIZE);
				}
			}
		}
	}
	
}
