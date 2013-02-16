package com.emveyh.antares.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.PooledLinkedList;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.utils.Coord;

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
		float newX = this.getX() + velocity * Gdx.graphics.getDeltaTime();
		float newY = this.getY();
		if (isValidPosition(newX, newY)) {
			this.setX(newX);
		}
	}

	public void moveY(float velocity) {
		float newX = this.getX();
		float newY = this.getY() + velocity * Gdx.graphics.getDeltaTime();
		if (isValidPosition(newX, newY)) {
			this.setY(newY);
		}
	}

	public boolean isValidPosition(float newX, float newY) {
		boolean result = false;
		if (!isCollidingWithTile(newX, newY)) {
			result = true;
		}

		return result;
	}

	public String toString() {
		return "position: [x=" + this.getX() + "] [y=" + this.getY() + "]";
	}

	public void tick() {
	}

	private boolean isCollidingWithTile(float xToCheck, float yToCheck) {
		boolean result = false;

		List<Coord> surroundingTiles = new LinkedList<Coord>();
		surroundingTiles.addAll(getSurroundingTilePositions(xToCheck / GlobalConfig.FIXED_TILESIZE, yToCheck / GlobalConfig.FIXED_TILESIZE));
		surroundingTiles
				.addAll(getSurroundingTilePositions((xToCheck + this.getWidth()) / GlobalConfig.FIXED_TILESIZE, yToCheck / GlobalConfig.FIXED_TILESIZE));
		surroundingTiles.addAll(getSurroundingTilePositions((xToCheck + this.getWidth()) / GlobalConfig.FIXED_TILESIZE, (yToCheck + this.getHeight())
				/ GlobalConfig.FIXED_TILESIZE));
		surroundingTiles.addAll(getSurroundingTilePositions(xToCheck, (yToCheck + this.getHeight()) / GlobalConfig.FIXED_TILESIZE));

		for (Coord coord : surroundingTiles) {
			if (coord.getX() >= 0 && coord.getY() >= 0 && coord.getX() < MapManager.getInstance().getGameMap().getWidth()
					&& coord.getY() < MapManager.getInstance().getGameMap().getHeight()) {
				if (!MapManager.getInstance().getGameMap().getTiles()[coord.getX()][coord.getY()].isAccessible()) {
					if (new Rectangle(xToCheck + 4, yToCheck + 4, this.getWidth() - 8, this.getHeight() - 8)
							.overlaps(new Rectangle(coord.getX() * GlobalConfig.FIXED_TILESIZE, coord.getY() * GlobalConfig.FIXED_TILESIZE,
									GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE))) {
						result = true;
					}
				}
			}
		}
		return result;
	}

	private List<Coord> getSurroundingTilePositions(float xToCheck, float yToCheck) {

		List<Coord> surroundingTilePositions = new ArrayList<Coord>();

		int xPos = (int) xToCheck;
		int yPos = (int) yToCheck;

		surroundingTilePositions.add(new Coord(xPos, yPos));

		if (xPos + 1 < MapManager.getInstance().getGameMap().getWidth()) {
			surroundingTilePositions.add(new Coord(xPos + 1, yPos));
		}
		if (xPos + 1 < MapManager.getInstance().getGameMap().getWidth() && yPos + 1 < MapManager.getInstance().getGameMap().getHeight()) {
			surroundingTilePositions.add(new Coord(xPos + 1, yPos + 1));
		}
		if (yPos + 1 < MapManager.getInstance().getGameMap().getHeight()) {
			surroundingTilePositions.add(new Coord(xPos, yPos + 1));
		}
		if (xPos - 1 >= 0) {
			surroundingTilePositions.add(new Coord(xPos - 1, yPos));
		}
		if (xPos - 1 >= 0 && yPos - 1 >= 0) {
			surroundingTilePositions.add(new Coord(xPos - 1, yPos - 1));
		}
		if (yPos - 1 >= 0) {
			surroundingTilePositions.add(new Coord(xPos, yPos - 1));
		}

		return surroundingTilePositions;
	}

}
