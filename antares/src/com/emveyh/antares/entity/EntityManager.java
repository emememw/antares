package com.emveyh.antares.entity;

import com.emveyh.antares.core.TextureManager;

public class EntityManager {

	private static final EntityManager INSTANCE = new EntityManager();

	public static EntityManager getInstance() {
		return EntityManager.INSTANCE;
	}

	private EntityManager() {
		player = new Player(0, 0, 200f, new EntityAnimationTextures(TextureManager.getInstance().getSprites()[5][0],
				TextureManager.getInstance().getSprites()[6][0], TextureManager.getInstance().getSprites()[7][0],
				TextureManager.getInstance().getSprites()[8][0], TextureManager.getInstance().getSprites()[3][0],
				TextureManager.getInstance().getSprites()[4][0]));
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
