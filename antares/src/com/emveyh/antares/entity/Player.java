package com.emveyh.antares.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends Entity {

	public Player(float x, float y, float speed, EntityAnimationTextures entityAnimationTextures) {
		super(x, y, speed, entityAnimationTextures);
	}
	
	@Override
	public void tickLogic() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			EntityManager.getInstance().getPlayer().moveX(true);
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			EntityManager.getInstance().getPlayer().moveX(false);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			EntityManager.getInstance().getPlayer().moveY(true);
		} else if(Gdx.input.isKeyPressed(Keys.UP)) {
			EntityManager.getInstance().getPlayer().moveY(false);
		}
	}

}
