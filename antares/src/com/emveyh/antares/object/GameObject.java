package com.emveyh.antares.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.core.GlobalConfig;

public abstract class GameObject extends Sprite {
	
	protected boolean accessible;
	
	protected int tileX;
	protected int tileY;
	
	public GameObject(TextureRegion textureRegion, boolean accessible, int tileX, int tileY) {
		this.setRegion(textureRegion);
		this.setX(tileX*GlobalConfig.FIXED_TILESIZE);
		this.setY(tileY*GlobalConfig.FIXED_TILESIZE);
		this.setSize(GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		this.accessible = accessible;
		this.tileX = tileX;
		this.tileY = tileY;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	
	protected void onTouch() {
		
	}
	
	protected void onHit() {
		
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	
	public void hit() {
		this.onHit();
	}
	
	
	
}
