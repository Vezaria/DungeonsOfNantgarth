package net.nantgarth;

import java.util.Random;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.gfx.Camera;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;

public final class Nantgarth {
	
	private Camera camera;
	private Renderer renderer;
	
	public Nantgarth() {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		this.camera = new Camera();
		this.renderer = new Renderer(camera);
		Window.addResizeHandler(renderer);
		
	}
	
	private void start () {
		int mapSize = 10;
		String[] map = new String[mapSize * mapSize];
		String[] tiles = {
				"sand",
				"stone",
				"grass"
		};
		Random r = new Random();
		for(int i = 0; i < map.length; i++) {
			map[i] = tiles[r.nextInt(tiles.length)];
		}
		
		while(Window.open()) {
			renderer.clear();
			renderer.start();
			
			for(int y = 0; y < mapSize; y++) {
				for(int x = 0; x < mapSize; x++) {
					renderer.tile(x, y, map[x + y * mapSize]);
				}
			}
			
			renderer.end();
			
			if(Input.key(GLFW.GLFW_KEY_W).held) {
				camera.getPosition().y += 0.01f;
			}
			if(Input.key(GLFW.GLFW_KEY_S).held) {
				camera.getPosition().y -= 0.01f;
			}
			if(Input.key(GLFW.GLFW_KEY_A).held) {
				camera.getPosition().x -= 0.01f;
			}
			if(Input.key(GLFW.GLFW_KEY_D).held) {
				camera.getPosition().x += 0.01f;
			}
			
			Input.update();
			Window.update();
		}
	}
	
	public static void main(String[] args) {
		Nantgarth game = new Nantgarth();
		game.start();
	}
}
