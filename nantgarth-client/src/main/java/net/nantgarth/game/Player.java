package net.nantgarth.game;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Animation;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.input.Input;
import net.nantgarth.math.Vector2f;
import net.nantgarth.world.Wall;

public class Player extends GameObject {

	private Vector2f velocity = new Vector2f();
	
	private Animation animation;
	
	private boolean direction = true;
	
	public Player() {
		this.animation = new Animation(new String[] {
				"player1", "player2", "player3", "player4", "player5", "player6"
		});
	}
	
	public void render(Nantgarth g, Renderer r) {
		if(velocity.x < 0) {
			direction = true;
		} else if(velocity.x > 0) {
			direction = false;
		}
		r.tile(position.x, position.y, animation.currentFrame() + (direction ? "L" : "R"));

		/*
		float left = position.x + 0.2f;
		float right = position.x + 0.8f;
		float bottom = position.y;
		float top = position.y + 0.3f;
			
		r.detailSide(left, position.y, "stonewall_dright");
		r.detailSide(right, position.y, "stonewall_dleft");
		r.detailTop(position.x, top, "stonewall_dtop");
		r.detailTop(position.x, bottom, "stonewall_dtop");
		*/
	}

	public void update(Nantgarth g, float dt) {
		float acceleration = 0.01f;
		velocity.x *= 0.85f;
		velocity.y *= 0.85f;
		
		if(Input.key(GLFW.GLFW_KEY_W).held) { velocity.y +=  acceleration * dt; }
		if(Input.key(GLFW.GLFW_KEY_S).held) { velocity.y += -acceleration * dt; }
		if(Input.key(GLFW.GLFW_KEY_A).held) { velocity.x += -acceleration * dt; }
		if(Input.key(GLFW.GLFW_KEY_D).held) { velocity.x +=  acceleration * dt; }

		if(velocity.x >  0.6f) { velocity.x =  0.06f; }
		if(velocity.x < -0.6f) { velocity.x = -0.06f; }
		if(velocity.y >  0.6f) { velocity.y =  0.06f; }
		if(velocity.y < -0.6f) { velocity.y = -0.06f; }

		float newX = position.x + velocity.x;
		float newY = position.y + velocity.y;
		
		float left = newX + 0.2f;
		float right = newX + 0.8f;
		float bottom = newY;
		float top = newY + 0.3f;
		
		boolean collision = false;
		
		// TODO: Make a workaround for this label.
		outer:
		for(int y = 0; y < g.level.getHeight(); y++) {
			for(int x = 0; x < g.level.getWidth(); x++) {
				Wall w = g.level.wallAt(x, y);
				if(w != null) {
					float tLeft = x;
					float tRight = x + 1f;
					float tBottom = y;
					float tTop = y + 1f;
					
					boolean yOverlap = false;
					boolean xOverlap = false;
					
					if(top > tBottom && bottom < tTop) yOverlap = true;
					if(right > tLeft && left < tRight) xOverlap = true;				
					
					collision = xOverlap && yOverlap;
					if(collision) {
						break outer;
					}
				}
			}
		}
		if(!collision) {
			position.x = newX;
			position.y = newY;			
		}
		
		
		if(Math.abs(velocity.x) < 0.000001f) velocity.x = 0;
		if(Math.abs(velocity.y) < 0.000001f) velocity.y = 0;
		
		animation.update();
	}
}
