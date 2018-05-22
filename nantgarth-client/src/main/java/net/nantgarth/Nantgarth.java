package net.nantgarth;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import net.nantgarth.gfx.TextureAtlas;
import net.nantgarth.gfx.Shader;
import net.nantgarth.gfx.SpriteBatch;
import net.nantgarth.gfx.Texture;
import net.nantgarth.gfx.Window;
import net.nantgarth.gfx.cb.ResizeHandler;
import net.nantgarth.input.Input;
import net.nantgarth.input.Input.MouseState;
import net.nantgarth.math.Matrix4f;

public class Nantgarth implements ResizeHandler {
	
	private Matrix4f ortho;
	
	public void start() {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		Window.addResizeHandler(this);
		
		GL.createCapabilities();

		TextureAtlas.generate();
		
		Shader shader = Shader.load("res/shaders/test.vs", "res/shaders/test.fs");
		
		float halfWidth = Window.getWidth() / 2f;
		float halfHeight = Window.getHeight() / 2f;
		ortho = Matrix4f.ortho(-halfWidth, halfWidth, -halfHeight, halfHeight, -1, 1);

		SpriteBatch batch = new SpriteBatch();
		
		float x = 0.0f * 200f;
		float y = 0.5f * 200f;
		
		float size = 500f;

		shader.bind();
		while(Window.open()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			shader.setMatrix4f("projection", ortho);

			MouseState mb1 = Input.mouse(GLFW.GLFW_MOUSE_BUTTON_1);
			if(mb1.held) {
				x = mb1.mx - (Window.getWidth() / 2f);
				y = -(mb1.my - (Window.getHeight() / 2f));
			}
			if(Input.key(GLFW.GLFW_KEY_Q).pressed) {
				size -= 10f;
			}
			if(Input.key(GLFW.GLFW_KEY_E).pressed) {
				size += 10f;
			}
			
			batch.begin();
			batch.submit(x, y, size, size, 0, 0, TextureAtlas.ATLAS_SIZE, TextureAtlas.ATLAS_SIZE);
			batch.submit(50, 50,  100, 100, "grass");
			batch.submit(50, 150, 100, 100, "stone");
			batch.submit(50, 250, 100, 100, "sand");
			batch.end();

			Input.update();
			Window.update();
		}
	}

	public void onResize(int newWidth, int newHeight) {
		GL11.glViewport(0, 0, newWidth, newHeight);

		// Recreate the projection matrix whenever the viewport changes.
		float halfWidth = newWidth / 2f;
		float halfHeight = newHeight / 2f;
		ortho = Matrix4f.ortho(-halfWidth, halfWidth, -halfHeight, halfHeight, -1, 1);
	}
	
	public static void main(String[] args) {
		Nantgarth game = new Nantgarth();
		game.start();
	}
}
