package com.emveyh.antares.object;

public enum GameObjectType {

	TREE(0,1,false),
	BERRY_BUSH(1,1,false);
	
	private int spritesIndexX;
	private int spritesIndexY;
	private boolean accessible;
	
	private GameObjectType(int spritesIndexX, int spritesIndexY, boolean accessible) {
		this.spritesIndexX = spritesIndexX;
		this.spritesIndexY = spritesIndexY;
		this.accessible = accessible;
	}

	public int getSpritesIndexX() {
		return spritesIndexX;
	}

	public int getSpritesIndexY() {
		return spritesIndexY;
	}

	public boolean isAccessible() {
		return accessible;
	}
	
	
	
}
