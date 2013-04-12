package com.emveyh.antares.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public class GameObject extends Sprite {
	
	protected boolean accessible;
	
	public GameObject(GameObjectType gameObjectType, float x, float y) {
		this.setRegion(TextureManager.getInstance().getSprites()[gameObjectType.getSpritesIndexX()][gameObjectType.getSpritesIndexY()]);
		this.setX(x);
		this.setY(y);
		this.setSize(GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		this.accessible = gameObjectType.isAccessible();
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	
	
}
