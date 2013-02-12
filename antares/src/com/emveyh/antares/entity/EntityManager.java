package com.emveyh.antares.entity;

import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.utils.Coord;

public class EntityManager {

	private static final EntityManager INSTANCE = new EntityManager();
	public static EntityManager getInstance() {
		return EntityManager.INSTANCE;
	}
	
	private EntityManager() {
		player = new Entity(TextureManager.getInstance().getSprites()[0][0], 0, 0, 3f);
		
	}
	
	private Entity player;
	public Entity getPlayer() {
		return player;
	}

	public void setPlayer(Entity player) {
		this.player = player;
	}
	
	public void tick() { 
		player.tick();
	}
	
	
	
}
