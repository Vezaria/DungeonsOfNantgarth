package net.nantgarth.gfx.buffers;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;

/**
 * Represents an OpenGL vertex buffer.
 */
public final class VBO {

	private int id;
	
	public VBO() {
		this.id = GL15.glGenBuffers();
	}

	public void bind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
	}
	
	public void unbind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Allocate memory on the GPU, without storing anything.
	 * @param size How much memory to allocate.
	 * @param usage How this data will be accessed, STATIC, DYNAMIC or STREAM.
	 */
	public void setData(long size, int usage) {
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, size, usage);
	}
	
	/**
	 * Upload some data to the GPU.
	 * @param data A floatbuffer containing the data to allocate.
	 */
	public void setSubData(FloatBuffer data) {
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, data);
	}
}
