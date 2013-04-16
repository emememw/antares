package com.emveyh.antares.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.entity.EntityManager;
import com.emveyh.antares.input.InputManager;
import com.emveyh.antares.map.MapManager;
import com.emveyh.antares.object.GameObjectManager;
import com.emveyh.antares.screen.ScreenManager;
import com.emveyh.antares.ui.UIManager;

public class GdxGame implements ApplicationListener {
	private SpriteBatch batch;
	
	@Override
	public void create() {
		int viewWidth = 800;
		int viewHeight = 480;
		
		
		TextureManager.getInstance().init();
		ScreenManager.getInstance().initialize();
		 Gdx.input.setInputProcessor(InputManager.getInstance());
		
		CameraManager.getInstance().initialize(viewWidth, viewHeight);
		CameraManager.getInstance().setSpotlightEntity(EntityManager.getInstance().getPlayer());
		GlobalConfig.getInstance().setViewWidth(viewWidth);
		GlobalConfig.getInstance().setViewHeight(viewHeight);
		GlobalConfig.getInstance().setCurrentGameState(GameState.NORMAL);
		
		batch = new SpriteBatch();
		
		MapManager.getInstance().loadWorld();
	}

	@Override
	public void dispose() {
		batch.dispose();
		TextureManager.getInstance().dispose();
	}

	@Override
	public void render() {
		CameraManager.getInstance().tick();
		
		Gdx.gl.glClearColor(0, 0,0,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(CameraManager.getInstance().getCamera().combined);
		batch.begin();
		
		ScreenManager.getInstance().render(batch);
		
		batch.end();
		
		EntityManager.getInstance().tick();
		//System.out.println(Gdx.graphics.getFramesPerSecond());
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
