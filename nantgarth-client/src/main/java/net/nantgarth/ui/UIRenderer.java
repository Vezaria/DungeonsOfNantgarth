package net.nantgarth.ui;

import net.nantgarth.gfx.Camera;
import net.nantgarth.gfx.Shader;
import net.nantgarth.gfx.SpriteBatch;
import net.nantgarth.gfx.Window;
import net.nantgarth.gfx.mesh.Mesher;
import net.nantgarth.math.Matrix4f;

public class UIRenderer implements Camera {

	private Matrix4f projection = null;
	private SpriteBatch spriteBatch;
	
	public UIRenderer() {
		updateProjection(Window.getWidth(), Window.getHeight());
		this.spriteBatch = new SpriteBatch(Shader.load("res/shaders/sprite.vs", "res/shaders/ui.fs"));
	}
	
	public void start() {
		spriteBatch.begin();
	}
	
	public void end() {
		spriteBatch.end(this);
	}
	
	public void sprite(float x, float y, float width, float height, String sprite) {
		spriteBatch.submit(x, y, sprite, Mesher.createFlipped(width, height, 0));
	}
	
	public Matrix4f getProjection() {
		return projection;
	}

	public Matrix4f getView() {
		return new Matrix4f(1.0f);
	}

	public void updateProjection(int windowWidth, int windowHeight) {
		projection = Matrix4f.ortho(0, windowWidth, windowHeight, 0, -1, 1);
	}
}
