package com.emveyh.antares.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public enum Tile {

	FLOOR(0,0,true),
	WALL(1,0,false);
	
	private int textureIndexX;
	private int textureIndexY;
	private boolean accessible;
	
	private Tile(int textureIndexX, int textureIndexY, boolean accessible) {
		this.textureIndexX = textureIndexX;
		this.textureIndexY = textureIndexY;
		this.accessible = accessible;
	}

	public boolean isAccessible() {
		return accessible;
	}
	
	public void render(SpriteBatch batch, float x, float y) {
		
		batch.draw(TextureManager.getInstance().getTiles()[textureIndexX][textureIndexY], x, y, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		
	}
	
}
