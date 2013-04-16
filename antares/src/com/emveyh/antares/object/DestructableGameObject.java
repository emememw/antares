package com.emveyh.antares.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DestructableGameObject extends GameObject {

	private int hitsTillDestroyed;
	
	public DestructableGameObject(TextureRegion textureRegion,int tileX, int tileY, int hitsTillDestroyed) {
		super(textureRegion, false, tileX, tileY);
		this.hitsTillDestroyed = hitsTillDestroyed;
	}

	@Override
	protected void onTouch() {
	}

	@Override
	protected void onHit() {
		hitsTillDestroyed--;
		if(hitsTillDestroyed == 0) {
			onDeath();
		}
	}

	protected void onDeath() {
		GameObjectManager.getInstance().removeGameObject(this);
	}
	
	

	

}
