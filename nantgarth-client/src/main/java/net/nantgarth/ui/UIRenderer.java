package net.nantgarth.ui;

import net.nantgarth.gfx.Camera;
import net.nantgarth.gfx.Shader;
import net.nantgarth.gfx.UIBatch;
import net.nantgarth.gfx.Window;
import net.nantgarth.gfx.font.Font;
import net.nantgarth.gfx.mesh.Mesher;
import net.nantgarth.math.Matrix4f;

public class UIRenderer implements Camera {

	private Matrix4f projection = null;
	private UIBatch spriteBatch;
	
	public UIRenderer() {
		updateProjection(Window.getWidth(), Window.getHeight());
		this.spriteBatch = new UIBatch(Shader.load("res/shaders/ui.vs", "res/shaders/ui.fs"));
	}
	
	public void start() {
		spriteBatch.begin();
	}
	
	public void end() {
		spriteBatch.end(this);
	}
	
	public void sprite(float x, float y, float width, float height, String sprite) {
		spriteBatch.submit(x, y, sprite, Mesher.createFlipped(width, height, 0, 0, 0, 0));
	}
	
	public void text(float x, float y, int scale, String str, float r, float g, float b, Font font) {
		for(char c : str.toCharArray()) {
			if(c == ' ') {
				x += font.getWidth(0) * scale;
			} else {				
				spriteBatch.submit(x, y, font.getName() + "_" + c, Mesher.createFlipped(font.getWidth(c) * scale, font.getHeight() * scale, 1, r, g, b));
				
				x += font.getWidth(c) * scale;				
			}
		}
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

	public float getLeft() { return 0; }
	public float getRight() {return 0; }
	public float getBottom() { return 0; }
	public float getTop() { return 0; }
}
