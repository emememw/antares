package com.emveyh.antares.ui.menu;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.emveyh.antares.core.CameraManager;
import com.emveyh.antares.core.FontManager;
import com.emveyh.antares.core.GlobalConfig;
import com.emveyh.antares.core.TextureManager;

public class Menu {

	private List<MenuOption> menuOptions = new LinkedList<MenuOption>();

	private int selectedMenuOptionIndex = 0;
	
	private String description;

	private float x;
	private float y;

	public Menu(float x, float y, String description) {
		this.x = x;
		this.y = y;
		this.description = description;
		menuOptions.add(new MenuOption("test1"));
		menuOptions.add(new MenuOption("test2"));
	}

	public void tick() {

	}

	public void render(SpriteBatch batch) {

		this.drawBorder(batch, 11, 6);
		float lineY = y+40;
		FontManager.getInstance().getFont().setScale(0.5f);
		FontManager.getInstance().getFont().draw(batch, description, CameraManager.getInstance().translateX(x+15), CameraManager.getInstance().translateY(lineY));
		for(MenuOption menuOption : menuOptions) {
			lineY-= 25;
			if(menuOptions.indexOf(menuOption) == selectedMenuOptionIndex) {
				FontManager.getInstance().getFont().draw(batch, ">", CameraManager.getInstance().translateX(x+25), CameraManager.getInstance().translateY(lineY));
			}
			FontManager.getInstance().getFont().draw(batch, menuOption.getDisplayText(), CameraManager.getInstance().translateX(x+50), CameraManager.getInstance().translateY(lineY));
		}

	}

	private void drawBorder(SpriteBatch batch, int width, int height) {

		batch.draw(TextureManager.getInstance().getSprites()[0][2], CameraManager.getInstance().translateX(x), CameraManager.getInstance().translateY(y),
				GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		for (int i = 1; i < width; i++) {
			batch.draw(TextureManager.getInstance().getSprites()[1][2], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * i,
					CameraManager.getInstance().translateY(y), GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		}
		batch.draw(TextureManager.getInstance().getSprites()[2][2], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * width,
				CameraManager.getInstance().translateY(y), GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);

		for (int i = 1; i < height; i++) {
			batch.draw(TextureManager.getInstance().getSprites()[0][3], CameraManager.getInstance().translateX(x), CameraManager.getInstance().translateY(y)
					- GlobalConfig.FIXED_TILESIZE * i, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
			for (int n = 1; n < width; n++) {
				batch.draw(TextureManager.getInstance().getSprites()[1][3], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * n,
						CameraManager.getInstance().translateY(y) - GlobalConfig.FIXED_TILESIZE * i, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
			}
			batch.draw(TextureManager.getInstance().getSprites()[2][3], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * width,
					CameraManager.getInstance().translateY(y) - GlobalConfig.FIXED_TILESIZE * i, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		}

		batch.draw(TextureManager.getInstance().getSprites()[0][4], CameraManager.getInstance().translateX(x), CameraManager.getInstance().translateY(y)
				- GlobalConfig.FIXED_TILESIZE * height, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		for (int i = 1; i < width; i++) {
			batch.draw(TextureManager.getInstance().getSprites()[1][4], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * i,
					CameraManager.getInstance().translateY(y) - GlobalConfig.FIXED_TILESIZE * height, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);
		}
		batch.draw(TextureManager.getInstance().getSprites()[2][4], CameraManager.getInstance().translateX(x) + GlobalConfig.FIXED_TILESIZE * width,
				CameraManager.getInstance().translateY(y) - GlobalConfig.FIXED_TILESIZE * height, GlobalConfig.FIXED_TILESIZE, GlobalConfig.FIXED_TILESIZE);

	}

	public List<MenuOption> getMenuOptions() {
		return menuOptions;
	}

	public int getSelectedMenuOptionIndex() {
		return selectedMenuOptionIndex;
	}

}
