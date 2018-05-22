package net.nantgarth.gfx;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import net.nantgarth.gfx.buffers.VAO;
import net.nantgarth.gfx.buffers.VBO;

/**
 * A <code>SpriteBatch</code> is used to efficiently draw multiple sprites whose textures are
 * contained within the same sprite atlas. As such, a sprite atlas must be provided to the SpriteBatch.
 */
public final class SpriteBatch {

	/**
	 * The size of the buffer. This may have to be increased at some point.
	 */
	private static final int BUFFER_SIZE = 4096;
	
	private FloatBuffer vertices;
	private int vertexCount;
	private boolean drawing;
	
	private VBO vbo;
	private VAO vao;
	
	public SpriteBatch() {
		this.vertices = MemoryUtil.memAllocFloat(BUFFER_SIZE);
		this.vertexCount = 0;
		this.drawing = false;
		initialize();
	}
	
	public void submit(float x, float y, float width, float height, String sprite) {
		if(!drawing) return;
		
		TextureCoordinates tc = TextureAtlas.get(sprite);
		submit(x, y, width, height, tc.s1, tc.t1, tc.s2, tc.t2);
	}
	
	public void submit(float x, float y, float width, float height, int px, int py, int pw, int ph) {
		if(!drawing) return;

	    float s1 = (float)px / (float)TextureAtlas.ATLAS_SIZE;
	    float t1 = (float)py / (float)TextureAtlas.ATLAS_SIZE;
	    
	    float s2 = (float)(px + pw) / (float)TextureAtlas.ATLAS_SIZE;
	    float t2 = (float)(py + ph) / (float)TextureAtlas.ATLAS_SIZE;
	    
	    submit(x, y, width, height, s1, t1, s2, t2);
	}
	
	public void submit(float x, float y, float width, float height, float s1, float t1, float s2, float t2) {
		if(!drawing) return;
		
	    float x1 = x;
	    float y1 = y;
	    float x2 = x + width;
	    float y2 = y + height;
	    
	    // Bottom left
	    vertices.put(x1);
	    vertices.put(y1);
	    vertices.put(s1);
	    vertices.put(t2);

	    // Top left
	    vertices.put(x1);
	    vertices.put(y2);
	    vertices.put(s1);
	    vertices.put(t1);

	    // Top right
	    vertices.put(x2);
	    vertices.put(y2);
	    vertices.put(s2);
	    vertices.put(t1);
	    
	    // Bottom left
	    vertices.put(x1);
	    vertices.put(y1);
	    vertices.put(s1);
	    vertices.put(t2);

	    // Top right
	    vertices.put(x2);
	    vertices.put(y2);
	    vertices.put(s2);
	    vertices.put(t1);
	    
	    // Bottom right
	    vertices.put(x2);
	    vertices.put(y1);
	    vertices.put(s2);
	    vertices.put(t2);
	    
	    vertexCount += 6;
	}
	
	private void initialize() {
		vao = new VAO();
		vao.bind();
		vbo = new VBO();
		vbo.bind();
		long size = vertices.capacity() * Float.BYTES;
		vbo.setData(size, GL15.GL_DYNAMIC_DRAW);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4 * Float.BYTES, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);
	}
	
	public void begin() {
		TextureAtlas.bind();
		vertexCount = 0;
		drawing = true;
	}
	
	public void end() {
		vertices.flip();
		vao.bind();
		// TODO: Bind shader
		vbo.bind();
		vbo.setSubData(vertices);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		
		vertices.clear();
		vertexCount = 0;
		
		drawing = false;
	}
}
