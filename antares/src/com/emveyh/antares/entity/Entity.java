package com.emveyh.antares.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public class Entity extends Sprite {

	private float speed;

	public Entity(TextureRegion texture, float x, float y, float speed) {
		this.setRegion(texture);
		this.setX(x);
		this.setY(y);
		this.setSize(GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		this.speed = speed;
	}

	public void render(SpriteBatch batch) {
		batch.draw(TextureManager.getInstance().getSprites()[2][0], this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public void moveX(boolean negative) {
		this.moveX(negative ? speed * -1 : speed);
	}

	public void moveY(boolean negative) {
		this.moveY(negative ? speed * -1 : speed);
	}

	public void moveX(float velocity) {
		this.setX(this.getX() + velocity - velocity * Gdx.graphics.getDeltaTime());
	}

	public void moveY(float velocity) {
		this.setY(this.getY() + velocity - velocity * Gdx.graphics.getDeltaTime());
	}

}
