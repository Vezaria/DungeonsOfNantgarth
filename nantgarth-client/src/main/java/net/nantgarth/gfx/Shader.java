package net.nantgarth.gfx;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import net.nantgarth.math.Matrix4f;
import net.nantgarth.util.FileUtil;

public class Shader {

	private int id;
	
	/**
	 * Constructs a shader given its source code.
	 * To load a shader from disk use <code>Shader.load();</code>
	 * @param vertSource The source of the vertex shader.
	 * @param fragSource The source of the fragment shader.
	 */
	public Shader(String vertSource, String fragSource) {
		this.id = create(vertSource, fragSource);
	}
	
	private int getUniformLocation(String name) {
		int loc = GL20.glGetUniformLocation(id, name);
		if(loc == -1) {
			throw new RuntimeException("Could not find uniform: " + name);
		}
		return loc;
	}

	public void setMatrix4f(String uniform, Matrix4f mat) {
		GL20.glUniformMatrix4fv(getUniformLocation(uniform), true, mat.e);
	}
	
	public void bind() {
		GL20.glUseProgram(id);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}

	private int create(String vertSource, String fragSource) {
		int program = GL20.glCreateProgram();
		int vertID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		int fragID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

		GL20.glShaderSource(vertID, vertSource);
		GL20.glShaderSource(fragID, fragSource);
		
		GL20.glCompileShader(vertID);
		if(GL20.glGetShaderi(vertID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(vertID));
			throw new RuntimeException("Failed to compile vertex shader.");
		}
		
		GL20.glCompileShader(fragID);
		if(GL20.glGetShaderi(fragID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(fragID));
			throw new RuntimeException("Failed to compile fragment shader.");
		}
		
		GL20.glAttachShader(program, vertID);
		GL20.glAttachShader(program, fragID);
		GL20.glLinkProgram(program);
		GL20.glValidateProgram(program);
		
		GL20.glDeleteShader(vertID);
		GL20.glDeleteShader(fragID);
		
		return program;
	}
	
	/**
	 * Loads a shader from disk.
	 * @param vertPath The path to the file containing the shaders vertex shader source code.
	 * @param fragPath The path to the file containing the shaders fragment shader source code.
	 * @return A shader object which can be used with OpenGL calls.
	 */
	public static Shader load(String vertPath, String fragPath) {
		String vertSource = FileUtil.read(vertPath);
		if(vertSource == null) {
			throw new RuntimeException("Could not read vertex shader source.");
		}
		
		String fragSource = FileUtil.read(fragPath);
		if(fragSource == null) {
			throw new RuntimeException("Could not read fragment shader source.");
		}
		
		return new Shader(vertSource, fragSource);
	}
}
