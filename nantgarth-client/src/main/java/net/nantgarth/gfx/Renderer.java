package net.nantgarth.gfx;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import net.nantgarth.gfx.cb.ResizeHandler;

public class Renderer implements ResizeHandler {

	private final Camera camera;
	
	private SpriteBatch spriteBatch;
	
	public Renderer(Camera camera) {
		this.camera = camera;
		init();
	}
	
	private void init() {
		// Make sure OpenGL has been set up for the current thread.
		GL.createCapabilities();
		
		// Now that OpenGL is operational, generate the texture atlas
		// and create the sprite batch.
		TextureAtlas.generate();
		this.spriteBatch = new SpriteBatch();
	}

	public void start() {
		spriteBatch.begin();
	}
	
	public void tile(float x, float y, String sprite) {
		if(x > camera.getLeft() - 1f && x < camera.getRight() && y < camera.getTop() && y > camera.getBottom() - 1f) {			
			spriteBatch.submit(x, y, 1, 1, sprite);
		}
	}
	
	public void end() {
		spriteBatch.end(camera);
	}
	
	public void onResize(int newWidth, int newHeight) {
		GL11.glViewport(0, 0, newWidth, newHeight);
		camera.updateProjection(newWidth, newHeight);
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
}
