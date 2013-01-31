package com.emveyh.antares.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.Coord;
import com.emveyh.antares.entity.Entity;

public class Tile {

	private Coord gridCoords;
	private boolean accessible;
	private TextureRegion texture;
	private Entity entity;
	
	public Tile(TextureRegion texture, boolean accessible, int gridX, int gridY) {
		gridCoords = new Coord(gridX, gridY);
		this.texture = texture;
		this.accessible = accessible;
	}

	public Coord getGridCoords() {
		return gridCoords;
	}

	public void setGridCoords(Coord gridCoords) {
		this.gridCoords = gridCoords;
	}


	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	
}
