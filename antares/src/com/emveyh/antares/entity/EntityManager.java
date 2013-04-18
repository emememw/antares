package com.emveyh.antares.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.GameState;
import com.emveyh.antares.core.GlobalConfig;
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
		if(GlobalConfig.getInstance().getCurrentGameState() == GameState.NORMAL) {
			player.tick();
		}
		if(GlobalConfig.getInstance().getCurrentGameState() == GameState.BUILD_MODE) {
			BuildingCursor.getInstance().tick();
		}
	}
	
	public void render(SpriteBatch batch) {
		player.draw(batch);
		if(GlobalConfig.getInstance().getCurrentGameState() == GameState.BUILD_MODE) {
			BuildingCursor.getInstance().render(batch);
		}
	}

}
