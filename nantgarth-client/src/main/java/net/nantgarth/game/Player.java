package net.nantgarth.game;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.input.Input;
import net.nantgarth.math.Vector2f;

public class Player extends GameObject {

	private Vector2f velocity = new Vector2f();
	
	public void render(Nantgarth g, Renderer r) {
		r.tile(position.x, position.y, "grass");
	}

	public void update(Nantgarth g, float dt) {
		float acceleration = 0.001f;

		velocity.x *= 0.99f;
		velocity.y *= 0.99f;
		
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
	}
}
