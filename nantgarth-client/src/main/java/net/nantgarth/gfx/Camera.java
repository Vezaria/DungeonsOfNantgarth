package net.nantgarth.gfx;

import net.nantgarth.game.GameObject;
import net.nantgarth.math.Matrix4f;
import net.nantgarth.math.Vector2f;

public class Camera {
	
	private Vector2f position;
	
	private Matrix4f projection;
	private float left, right, bottom, top;
	
	private GameObject follow = null;
	
	public Camera() {
		this.position = new Vector2f();
		updateProjection(Window.getWidth(), Window.getHeight());
	}
	
	public void updateProjection(int windowWidth, int windowHeight) {
		float viewWidth = windowWidth / 200f;
		float viewHeight = windowHeight / 200f;
		this.left = -viewWidth;
		this.right = viewWidth;
		this.bottom = -viewHeight;
		this.top = viewHeight;
		projection = Matrix4f.ortho(left, right, bottom, top, -1, 1);
	}
	
	public void update() {
		if(follow != null) {
			// Add 0.5 so that the camera is centered on the 1 unit by 1 unit player.
			this.position.x = follow.getPosition().x + 0.5f;
			this.position.y = follow.getPosition().y + 0.5f;
		}
	}
	
	public void setFollow(GameObject o) {
		this.follow = o;
	}
	
	public float getTop() {
		return top + position.y;
	}
	
	public float getBottom() {
		return bottom + position.y;
	}
	
	public float getLeft() {
		return left + position.x;
	}
	
	public float getRight() {
		return right + position.x;
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
