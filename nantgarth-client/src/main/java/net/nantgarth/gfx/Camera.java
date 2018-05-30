package net.nantgarth.gfx;

import net.nantgarth.math.Matrix4f;

public interface Camera {

	public Matrix4f getProjection();
	public Matrix4f getView();
	
	public default void updateProjection(int width, int height){
		
	}
	
	public float getLeft();
	public float getRight();
	public float getBottom();
	public float getTop();
}
