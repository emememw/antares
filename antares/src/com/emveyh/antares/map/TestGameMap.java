package com.emveyh.antares.map;

import java.util.Random;

import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.entity.EntityManager;

public class TestGameMap extends GameMap {

	public TestGameMap() {
		super(30, 30);
		generate();
	}
	
	public void generate() {
		Random random = new Random();
		for(int x = 0; x < this.getWidth(); x++) {
			for(int y = 0; y < this.getHeight(); y++) {
				int chance = random.nextInt(100);
				if(chance < 20) {
					this.getTiles()[x][y] = Tile.WALL;
				} else {
					EntityManager.getInstance().getPlayer().setX(x*GlobalConfig.FIXED_TILESIZE);
					EntityManager.getInstance().getPlayer().setY(y*GlobalConfig.FIXED_TILESIZE);
					this.getTiles()[x][y] = Tile.FLOOR;
				}
			}
		}
		
		
		
	}
	
}
