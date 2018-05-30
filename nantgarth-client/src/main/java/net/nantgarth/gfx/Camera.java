package net.nantgarth.gfx;

import net.nantgarth.math.Matrix4f;

public interface Camera {

	public Matrix4f getProjection();
	public Matrix4f getView();
}
