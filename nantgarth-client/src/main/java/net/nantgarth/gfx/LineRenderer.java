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
import net.nantgarth.math.Vector3f;

public final class LineRenderer {

	private static final int BUFFER_SIZE = 4096 * 8;

	private static final String VERTEX = "res/shaders/line.vs";
	private static final String FRAGMENT = "res/shaders/line.fs";
	
	private FloatBuffer vertices;
	private int vertexCount;
	private boolean drawing;
	
	private VBO vbo;
	private VAO vao;

	private Shader shader;
	
	public LineRenderer() {
		this.vertices = MemoryUtil.memAllocFloat(BUFFER_SIZE);
		this.vertexCount = 0;
		this.drawing = false;
		this.shader = Shader.load(VERTEX, FRAGMENT);
		initialize();
	}
	
	public void submit(float x1, float y1, float x2, float y2, float r, float g, float b) {
		if(!drawing) return;

	    vertices.put(x1);
	    vertices.put(y1);
	    vertices.put(r);
	    vertices.put(g);
	    vertices.put(b);

	    vertices.put(x2);
	    vertices.put(y2);
	    vertices.put(r);
	    vertices.put(g);
	    vertices.put(b);
	    
		vertexCount += 2;
	}
	
	public void rect(float x, float y, float width, float height, float r, float g, float b) {
		submit(x, y, x + width, y, r, g, b);
		submit(x, y, x, y + height, r, g, b);

		submit(x + width, y, x + width, y + height, r, g, b);
		submit(x, y + height, x + width, y + height, r, g, b);
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
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 5 * Float.BYTES, 0);
		GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 5 * Float.BYTES, 2 * Float.BYTES);
	}
	
	public void begin() {
		vertexCount = 0;
		drawing = true;
	}
	
	public void end(FollowCamera camera) {
		vertices.flip();
		vao.bind();
		shader.bind();
		shader.setMatrix4f("projection", camera.getProjection());
		shader.setMatrix4f("view", camera.getView());
		vbo.bind();
		vbo.setSubData(vertices);
		
		GL11.glDrawArrays(GL11.GL_LINES, 0, vertexCount);
		
		vertices.clear();
		vertexCount = 0;
		
		drawing = false;
	}
}
