package com.emveyh.antares.item;

import java.util.ArrayList;

public class Container extends Item {

	private int inmaxsize;
	private int inmaxweight;
	private ArrayList<Item> content = new ArrayList<Item>();
	private int usedsize;
	private int usedweight;
	
	public Container(String name, float weight, float size, int quality, int inmaxsize, int inmaxweight) {
		super(name, weight, size, quality);
		this.inmaxsize = inmaxsize;
		this.inmaxweight = inmaxweight;
	
	}

	public boolean addItem(Item newItem){
		
		boolean added = false;
		
		if (!(usedsize + newItem.getSize() > inmaxsize && usedweight + newItem.getWeight() > inmaxweight)){
			content.add(newItem);
			added = true;
		}
		
		if(usedsize + newItem.getSize() > inmaxsize){
			//gib Meldung: zu groß
		}
		if (usedweight + newItem.getWeight() > inmaxweight){
			//gib Meldung: zu schwer
		}
		return added;
		
	}
	
	
}
