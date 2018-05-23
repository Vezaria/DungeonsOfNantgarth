package net.nantgarth.gfx;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import net.nantgarth.gfx.buffers.VAO;
import net.nantgarth.gfx.buffers.VBO;
import net.nantgarth.gfx.mesh.Mesher;
import net.nantgarth.math.Vector2f;

/**
 * A <code>SpriteBatch</code> is used to efficiently draw multiple sprites whose textures are
 * contained within the same sprite atlas. As such, a sprite atlas must be provided to the SpriteBatch.
 */
public final class SpriteBatch {
	
	/**
	 * The size of the buffer. This may have to be increased at some point.
	 */
	private static final int BUFFER_SIZE = 4096 * 8;

	private static final String VERTEX = "res/shaders/test.vs";
	private static final String FRAGMENT = "res/shaders/test.fs";
	
	private FloatBuffer vertices;
	private int vertexCount;
	private boolean drawing;
	
	private VBO vbo;
	private VAO vao;

	private Shader shader;
	
	public SpriteBatch() {
		this.vertices = MemoryUtil.memAllocFloat(BUFFER_SIZE);
		this.vertexCount = 0;
		this.drawing = false;
		this.shader = Shader.load(VERTEX, FRAGMENT);
		initialize();
	}
	
	public void submit(float x, float y, String sprite, Mesher mesher) {
		if(!drawing) return;
		TextureCoordinates tc = TextureAtlas.get(sprite);
		vertexCount += mesher.generate(vertices, new Vector2f(x, y), tc);
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
	
	public void end(Camera camera) {
		vertices.flip();
		vao.bind();
		shader.bind();
		shader.setMatrix4f("projection", camera.getProjection());
		shader.setMatrix4f("view", camera.getView());
		vbo.bind();
		vbo.setSubData(vertices);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		
		vertices.clear();
		vertexCount = 0;
		
		drawing = false;
	}
}
