package com.emveyh.antares.map;

public enum WorldTileType {

	WATER(0),
	LAND(1),
	BEACH(2);
	
	private int id;
	
	private WorldTileType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
