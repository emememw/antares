package com.emveyh.antares.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.emveyh.antares.entity.Entity;
import com.emveyh.antares.map.MapManager;

public class CameraManager {

	private static final CameraManager INSTANCE = new CameraManager();

	public static CameraManager getInstance() {
		return CameraManager.INSTANCE;
	}

	private CameraManager() {
	}

	private OrthographicCamera camera;
	private Entity spotlightEntity;

	public void initialize(float viewWidth, float viewHeight) {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, viewWidth, viewHeight);
	}

	public void tick() {
		if (spotlightEntity != null) {
			if (spotlightEntity.getX() - camera.viewportWidth / 2 < 0) {
				camera.position.x = camera.viewportWidth / 2;
			} else if (spotlightEntity.getX() + camera.viewportWidth / 2 > MapManager.getInstance().getCurrentMap().getWidth() * GlobalConfig.FIXED_TILESIZE) {

				camera.position.x = MapManager.getInstance().getCurrentMap().getWidth() * GlobalConfig.FIXED_TILESIZE - camera.viewportWidth / 2;
			} else {
				camera.position.x = spotlightEntity.getX();
			}
			
			if (spotlightEntity.getY() - camera.viewportHeight / 2 < 0) {
				camera.position.y = camera.viewportHeight / 2;
			} else if (spotlightEntity.getY() + camera.viewportHeight / 2 > MapManager.getInstance().getCurrentMap().getHeight() * GlobalConfig.FIXED_TILESIZE) {

				camera.position.y = MapManager.getInstance().getCurrentMap().getHeight() * GlobalConfig.FIXED_TILESIZE - camera.viewportHeight / 2;
			} else {
				camera.position.y = spotlightEntity.getY();
			}
			
			camera.update();
		}
	}

	public Entity getSpotlightEntity() {
		return spotlightEntity;
	}

	public void setSpotlightEntity(Entity spotlightEntity) {
		this.spotlightEntity = spotlightEntity;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public float translateX(float x) {
		return camera.position.x-camera.viewportWidth/2+x;
	}
	
	public float translateY(float y) {
		return camera.position.y-camera.viewportHeight/2+y;
	}

}
