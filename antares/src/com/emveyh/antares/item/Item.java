package com.emveyh.antares.item;

public class Item {
	
	
	public Item(String name, float weight,float size, int quality){
		this.name = name;
		this.size = size;
		this.weight = weight;
		this.quality = quality;
		
		
	}
	
	
	private String name;
	private float weight;
	private int quality;
	private float size;

	
	//weiß net wie sinnig das ist
	private int textureIndexX;
	private int textureIndexY;
	
	
	public float getWeight() {
		return weight;
	}
	public float getSize() {
		return size;
	}

	
	
	
}
