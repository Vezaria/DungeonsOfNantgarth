package net.nantgarth;

import java.util.ArrayList;

import net.nantgarth.game.GameObject;
import net.nantgarth.game.Player;
import net.nantgarth.gfx.Camera;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;
import net.nantgarth.world.Level;

public final class Nantgarth {

	private Camera camera;
	private Renderer renderer;
	private Level level;

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private Player player;

	public Nantgarth() {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		this.camera = new Camera();
		this.renderer = new Renderer(camera);
		Window.addResizeHandler(renderer);
		this.level = new Level(20, 20);

		this.player = new Player();
		this.camera.setFollow(player);
		this.gameObjects.add(player);
	}

	private void start() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (Window.open()) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			float dt = updateLength / ((float) OPTIMAL_TIME);

			for (GameObject go : gameObjects) {
				go.update(this, dt);
			}

			renderer.clear();
			renderer.start();

			renderer.level(level);

			for (GameObject go : gameObjects) {
				go.render(this, renderer);
			}

			renderer.end();

			camera.update();
			Input.update();
			Window.update();
		}
		// On one computer I tested this on the program hangs on window close.
		// This fixes that, although it may be crude.
		Window.close();
		System.exit(0);
	}

	public static void main(String[] args) {
		Nantgarth game = new Nantgarth();
		game.start();
	}
}
