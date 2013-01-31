package com.emveyh.antares.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.Coord;

public class Entity extends Sprite {

	private Coord gridCoord;
	
	public Entity(TextureRegion texture) {
		this.setRegion(texture);
	}

	public Coord getGridCoord() {
		return gridCoord;
	}

	public void setGridCoord(Coord gridCoord) {
		this.gridCoord = gridCoord;
	}

	

	
	
	
	
	
}
