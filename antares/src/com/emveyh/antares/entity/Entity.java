package com.emveyh.antares.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.utils.Coord;
import com.emveyh.antares.utils.Direction;

public class Entity extends Sprite {

	private float speed;
	private EntityAnimationTextures entityAnimationTextures;
	private boolean walking;
	private float walktime;
	private Direction currentDirection = Direction.DOWN;
	private TextureRegion currentTexture;

	public Entity(float x, float y, float speed, EntityAnimationTextures entityAnimationTextures) {
		this.setCurrentTexture(entityAnimationTextures.getTextureWalkDown1());
		this.entityAnimationTextures = entityAnimationTextures;
		this.setX(x);
		this.setY(y);
		this.setSize(GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		this.speed = speed;
	}

	public EntityAnimationTextures getEntityAnimationTextures() {
		return entityAnimationTextures;
	}

	public void setEntityAnimationTextures(EntityAnimationTextures entityAnimationTextures) {
		this.entityAnimationTextures = entityAnimationTextures;
	}

	public void render(SpriteBatch batch) {
		batch.draw(this, this.getX(), this.getY(), this.getWidth(), this.getHeight());
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
			walking = true;
		}
		if(velocity > 0) {
			this.currentDirection = Direction.RIGHT;
		} else {
			this.currentDirection = Direction.LEFT;
		}
	}

	public void moveY(float velocity) {
		float newX = this.getX();
		float newY = this.getY() + velocity * Gdx.graphics.getDeltaTime();
		if (isValidPosition(newX, newY)) {
			this.setY(newY);
			walking = true;
		}
		if(velocity > 0) {
			this.currentDirection = Direction.UP;
		} else {
			this.currentDirection = Direction.DOWN;
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
		walking = false;
		Direction previousDirection = currentDirection;
		tickLogic();
		if(previousDirection != currentDirection) {
			changeWalkingTexture();
		}
		if(walking) {
			walktime += Gdx.graphics.getDeltaTime();
			if(walktime > 0.2f) {
				changeWalkingTexture();
				walktime = 0;
			}
		} else {
			walktime = 0;
		}
	}
	
	public void tickLogic() {
		
	}
	
	private void changeWalkingTexture() {
		if(this.currentDirection == Direction.RIGHT || this.currentDirection == Direction.LEFT) {
			if(this.currentTexture == this.entityAnimationTextures.getTextureWalkHorizontal1()) {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkHorizontal2());
			} else {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkHorizontal1());
			}
			if(this.currentDirection == Direction.LEFT) {
				this.flip(true, false);
			}
		}  else if(this.currentDirection == Direction.DOWN) {
			if(this.currentTexture == this.entityAnimationTextures.getTextureWalkDown1()) {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkDown2());
			} else {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkDown1());
			}
		} else if(this.currentDirection == Direction.UP) {
			if(this.currentTexture == this.entityAnimationTextures.getTextureWalkUp1()) {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkUp2());
			} else {
				this.setCurrentTexture(this.entityAnimationTextures.getTextureWalkUp1());
			}
		}
		
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
	
	protected Coord getTileNextToEntity() {
		Coord result = new Coord(0,0);
		int xPos = 0;
		int yPos = 0;
		if(this.currentDirection == Direction.RIGHT) {
			xPos = (int) (this.getX()-4+(this.getWidth()))/GlobalConfig.FIXED_TILESIZE+1;
			yPos = (int) (this.getY()+(this.getHeight()/2))/GlobalConfig.FIXED_TILESIZE;
		} else if(this.currentDirection == Direction.LEFT) {
			xPos = (int) (this.getX()+4)/GlobalConfig.FIXED_TILESIZE-1;
			yPos = (int) (this.getY()+(this.getHeight()/2))/GlobalConfig.FIXED_TILESIZE;
		} else if(this.currentDirection == Direction.UP) {
			xPos = (int) (this.getX()+(this.getWidth()/2))/GlobalConfig.FIXED_TILESIZE;
			yPos = (int) (this.getY()-4+this.getHeight())/GlobalConfig.FIXED_TILESIZE+1;
		} else if(this.currentDirection == Direction.DOWN) {
			xPos = (int) (this.getX()+(this.getWidth()/2))/GlobalConfig.FIXED_TILESIZE;
			yPos = (int) (this.getY()+4)/GlobalConfig.FIXED_TILESIZE-1;
		}
		result.setX(xPos);
		result.setY(yPos);
		System.out.println(xPos+" "+yPos);
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
	
	public void setCurrentTexture(TextureRegion texture) {
		this.currentTexture = texture;
		this.setRegion(texture);
	}

}
