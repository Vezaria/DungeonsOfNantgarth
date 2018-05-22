package net.nantgarth.gfx.buffers;

import org.lwjgl.opengl.GL30;

/**
 * Represents an OpenGL vertex array.
 */
public final class VAO {

	private int id;
	
	public VAO() {
		this.id = GL30.glGenVertexArrays();
	}
	
	public void bind() {
		GL30.glBindVertexArray(id);
	}
	
	public void unbind() {
		GL30.glBindVertexArray(0);
	}
}
