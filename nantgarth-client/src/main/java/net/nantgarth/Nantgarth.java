package net.nantgarth;

import net.nantgarth.gfx.Renderer;
import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;
import net.nantgarth.state.GameState;
import net.nantgarth.state.StateManager;

public final class Nantgarth {

	private Renderer renderer;

	public Nantgarth() {
		Window.initialize(1280, 720, "Dungeons of Nantgarth");
		this.renderer = new Renderer();
		Window.addResizeHandler(renderer);
		
		StateManager.pushState(new GameState());
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

			StateManager.currentState().update(this, dt);

			renderer.clear();
			renderer.start();
			
			StateManager.currentState().render(this, renderer);
			
			renderer.end();
			
			StateManager.currentState().postRender(this);
			
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
