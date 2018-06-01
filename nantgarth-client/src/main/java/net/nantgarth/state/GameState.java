package net.nantgarth.state;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.Nantgarth;
import net.nantgarth.game.GameObject;
import net.nantgarth.game.Player;
import net.nantgarth.gfx.FollowCamera;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.gfx.Window;
import net.nantgarth.input.Input;
import net.nantgarth.math.Vector2f;
import net.nantgarth.ui.Minimap;
import net.nantgarth.ui.PartyHUD;
import net.nantgarth.ui.UIManager;
import net.nantgarth.ui.UIWindow;
import net.nantgarth.world.Level;

public class GameState extends State {

	private Level level;
	private UIManager uiManager;
	private FollowCamera camera;

	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private Player player;
	
	public GameState() {
		this.level = new Level(20, 20);
		this.camera = new FollowCamera();

		this.uiManager = new UIManager();
		this.uiManager.add(new PartyHUD());
		this.uiManager.add(new Minimap());
		this.uiManager.add(new UIWindow());
		Window.addResizeHandler(uiManager);

		this.player = new Player(level);
		this.camera.setFollow(player);
		this.gameObjects.add(player);
	}
	
	public void render(Nantgarth g, Renderer renderer) {
		if(renderer.getCamera() == null) {
			renderer.setCamera(camera);
		}
		
		renderer.level(level, gameObjects, g);
		
		if(Input.mouse(GLFW.GLFW_MOUSE_BUTTON_1).pressed) {
			Vector2f pos = camera.mouseToWorld(Input.mouseX(), Input.mouseY());
			int tileX = (int)pos.x;
			int tileY = (int)pos.y;
			
			level.walls[tileX + tileY * level.getWidth()] = null;
		}
	}
	
	public void postRender(Nantgarth nantgarth) {
		uiManager.render();
	}

	public void update(Nantgarth g, float dt) {
		for (GameObject go : gameObjects) {
			go.update(g, dt);
		}
		camera.update(dt);
	}
}
