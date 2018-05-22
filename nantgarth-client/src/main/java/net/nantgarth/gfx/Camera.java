package net.nantgarth.gfx;

import net.nantgarth.math.Matrix4f;
import net.nantgarth.math.Vector2f;

public class Camera {
	
	private Vector2f position;
	
	private Matrix4f projection;
	
	public Camera() {
		this.position = new Vector2f();
		updateProjection(Window.getWidth(), Window.getHeight());
	}
	
	public void updateProjection(int windowWidth, int windowHeight) {
		float halfWidth = windowWidth / 200f;
		float halfHeight = windowHeight / 200f;
		projection = Matrix4f.ortho(-halfWidth, halfWidth, -halfHeight, halfHeight, -1, 1);
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Matrix4f getProjection() {
		return projection;
	}
	
	public Matrix4f getView() {
		Matrix4f res = Matrix4f.translate(-position.x, -position.y, 0);
		return res;
	}
}
