package net.nantgarth.gfx;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import net.nantgarth.Nantgarth;
import net.nantgarth.game.GameObject;
import net.nantgarth.game.Player;
import net.nantgarth.gfx.cb.ResizeHandler;
import net.nantgarth.gfx.mesh.Mesher;
import net.nantgarth.world.Floor;
import net.nantgarth.world.Level;
import net.nantgarth.world.Wall;

public class Renderer implements ResizeHandler {

	private final Camera camera;
	
	private SpriteBatch spriteBatch;
	
	public Renderer(Camera camera) {
		this.camera = camera;
		init();
	}
	
	private void init() {
		// Make sure OpenGL has been set up for the current thread.
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		// Now that OpenGL is operational, generate the texture atlas
		// and create the sprite batch.
		TextureAtlas.generate();
		this.spriteBatch = new SpriteBatch();
	}

	public void start() {
		spriteBatch.begin();
	}
	
	public void tile(float x, float y, String sprite) {
		if(x > camera.getLeft() - 1f && x < camera.getRight() && y < camera.getTop() && y > camera.getBottom() - 1f) {
			spriteBatch.submit(x, y, sprite, Mesher.FLOOR);
		}
	}
	
	public void wall(float x, float y, String sprite) {
		if(x > camera.getLeft() - 1f && x < camera.getRight() && y < camera.getTop() && y > camera.getBottom() - 1f) {
			spriteBatch.submit(x, y, sprite, Mesher.WALL);
		}
	}
	
	public void detailSide(float x, float y, String sprite) {
		if(x > camera.getLeft() - 1f && x < camera.getRight() && y < camera.getTop() && y > camera.getBottom() - 1f) {
			spriteBatch.submit(x, y, sprite, Mesher.DETAIL_SIDE);
		}
	}
	
	public void detailTop(float x, float y, String sprite) {
		if(x > camera.getLeft() - 1f && x < camera.getRight() && y < camera.getTop() && y > camera.getBottom() - 1f) {
			spriteBatch.submit(x, y, sprite, Mesher.DETAIL_TOP);
		}
	}
	
	public void rotateTest(float x, float y, float r) {
		spriteBatch.submit(x, y, "sand", Mesher.create(2, 2, r));
		
	}
	
	public void level(Level level, ArrayList<GameObject> objects, Nantgarth g) {
		for(int y = level.getHeight()-1; y >= 0; y--) {
			for(int x = 0; x < level.getWidth(); x++) {
				Floor floor = level.floors[x + y * level.getWidth()];
				Wall wall = level.walls[x + y * level.getWidth()];
				if(floor != null) {
					tile(x, y, floor.sprite);
				}
				if(wall != null) {
					wall(x, y, wall.sprite);
				}
			}
		}
		
		for (GameObject go : objects) {
			go.render(g, this);
		}
		
		float p = 1f / 16f;
		for(int y = level.getHeight()-1; y >= 0; y--) {
			for(int x = 0; x < level.getWidth(); x++) {
				Wall wall = level.walls[x + y * level.getWidth()];
				
				if(wall != null) {
					tile(x, y + 0.5f, wall.sprite + "_top");
				}
				
				Wall north = level.wallAt(x, y + 1);
				Wall east = level.wallAt(x + 1, y);
				Wall south = level.wallAt(x, y - 1);
				Wall west = level.wallAt(x - 1, y);
				if(wall != null) {
					if(south == null) {						
						wall(x, y - 0.5f, wall.sprite + "_shadow");
					}
					if(west == null) {						
						detailSide(x - (p * 2f), y + 0.5f, wall.sprite + "_dleft");
					}
					if(east == null) {
						detailSide(x + 1.0f, y + 0.5f, wall.sprite + "_dright");
					}
					if(north == null) {
						detailTop(x, y + 1.5f, wall.sprite + "_dtop");
					}
				}
			}
		}
	}
	
	public void end() {
		spriteBatch.end(camera);
	}
	
	public void onResize(int newWidth, int newHeight) {
		GL11.glViewport(0, 0, newWidth, newHeight);
		camera.updateProjection(newWidth, newHeight);
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
}
