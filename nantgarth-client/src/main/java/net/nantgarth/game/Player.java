package net.nantgarth.game;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Animation;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.input.Input;
import net.nantgarth.math.Vector2f;

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
	}

	public void update(Nantgarth g, float dt) {
		//float acceleration = 0.001f;
		//velocity.x *= 0.99f;
		//velocity.y *= 0.99f;
		
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
		
		position.x += velocity.x;
		position.y += velocity.y;

		if(Math.abs(velocity.x) < 0.000001f) velocity.x = 0;
		if(Math.abs(velocity.y) < 0.000001f) velocity.y = 0;
		
		animation.update();
	}
}
