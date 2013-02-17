package com.emveyh.antares.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EntityAnimationTextures {

	private TextureRegion textureWalkHorizontal1;
	private TextureRegion textureWalkHorizontal2;
	private TextureRegion textureWalkUp1;
	private TextureRegion textureWalkUp2;
	private TextureRegion textureWalkDown1;
	private TextureRegion textureWalkDown2;
	
	public EntityAnimationTextures(TextureRegion textureWalkHorizontal1, TextureRegion textureWalkHorizontal2, TextureRegion textureWalkUp1, TextureRegion textureWalkUp2, TextureRegion textureWalkDown1, TextureRegion textureWalkDown2) {
		this.textureWalkHorizontal1 = textureWalkHorizontal1;
		this.textureWalkHorizontal2 = textureWalkHorizontal2;
		this.textureWalkUp1 = textureWalkUp1;
		this.textureWalkUp2 = textureWalkUp2;
		this.textureWalkDown1 = textureWalkDown1;
		this.textureWalkDown2 = textureWalkDown2;
	}
	
	public TextureRegion getTextureWalkHorizontal1() {
		return textureWalkHorizontal1;
	}
	public void setTextureWalkHorizontal1(TextureRegion textureWalkHorizontal1) {
		this.textureWalkHorizontal1 = textureWalkHorizontal1;
	}
	public TextureRegion getTextureWalkHorizontal2() {
		return textureWalkHorizontal2;
	}
	public void setTextureWalkHorizontal2(TextureRegion textureWalkHorizontal2) {
		this.textureWalkHorizontal2 = textureWalkHorizontal2;
	}
	public TextureRegion getTextureWalkUp1() {
		return textureWalkUp1;
	}
	public void setTextureWalkUp1(TextureRegion textureWalkUp1) {
		this.textureWalkUp1 = textureWalkUp1;
	}
	public TextureRegion getTextureWalkUp2() {
		return textureWalkUp2;
	}
	public void setTextureWalkUp2(TextureRegion textureWalkUp2) {
		this.textureWalkUp2 = textureWalkUp2;
	}
	public TextureRegion getTextureWalkDown1() {
		return textureWalkDown1;
	}
	public void setTextureWalkDown1(TextureRegion textureWalkDown1) {
		this.textureWalkDown1 = textureWalkDown1;
	}
	public TextureRegion getTextureWalkDown2() {
		return textureWalkDown2;
	}
	public void setTextureWalkDown2(TextureRegion textureWalkDown2) {
		this.textureWalkDown2 = textureWalkDown2;
	}
	
	
	
}
