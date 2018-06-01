package net.nantgarth.game;

import org.lwjgl.glfw.GLFW;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Animation;
import net.nantgarth.gfx.FollowCamera;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.input.Input;
import net.nantgarth.math.Vector2f;
import net.nantgarth.world.Level;
import net.nantgarth.world.Wall;

public class Player extends GameObject {

	private Vector2f velocity = new Vector2f();
	
	private Animation animation;
	private boolean direction = true;
	
	// The level the player is currently in
	// This information should be moved up to game object
	private Level level = null;
	
	private boolean swinging = false;
	private boolean returning = false;
	private float weaponOffset = 0;
	
	public Player(Level level) {
		this.animation = new Animation(new String[] {
				"player1", "player2", "player3", "player4", "player5", "player6"
		});
		this.level = level;
	}
	
	public void render(Nantgarth g, Renderer r) {
		r.tile(position.x, position.y, animation.currentFrame() + (direction ? "L" : "R"));

		//float left = position.x + 0.2f;
		//float right = position.x + 0.8f;
		//float bottom = position.y;
		//float top = position.y + 0.4f;
		// Visualize the players hitbox
		//r.rect(left, bottom, right - left, top - bottom, 1, 0, 0);
		
		// Visualize the players velocity
		//Vector2f vel = velocity.normalize();
		//r.line(position.x + 0.5f, position.y + 0.5f, (position.x + 0.5f) + (vel.x), (position.y + 0.5f) + (vel.y), 0, 1, 1);
		
		if(r.getCamera() instanceof FollowCamera) {
			FollowCamera fc = (FollowCamera)r.getCamera();
			Vector2f mp = fc.mouseToWorld(Input.mouseX(), Input.mouseY());
			
			float ratio = 1f / 14f;
			
			if(mp.x + (ratio * 12f) < position.x + (ratio * 12f) || velocity.x < 0) {
				direction = true;
			} else {
				direction = false;
			}
			
			mp.x -= 0.3f;
			mp.y -= 0.3f;
			float angle = (float) Math.atan2(mp.y - position.y, mp.x - position.x);
			if(direction) {
				angle += Math.toRadians(180);
			}
			angle += Math.toRadians(90);
			angle += direction ? Math.toRadians(weaponOffset) : -Math.toRadians(weaponOffset);
			if(direction) {
				r.sprite(position.x + 0.6f, position.y + 0.3f, 1, 1f / 6f, angle, 1f/7.5f, (1f/6f)/2f, "weapon");
			} else {
				r.sprite(position.x + 0.3f, position.y + 0.3f, 1, 1f / 6f, angle, 1f/7.5f, (1f/6f)/2f, "weapon");				
			}
		}
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
		float top = newY + 0.4f;
		
		boolean collision = collision(left, right, position.y, position.y + 0.4f, level);
		if(!collision) {
			position.x = newX;
		}
		collision = collision(position.x + 0.2f, position.x + 0.8f, bottom, top, level);
		if(!collision) {
			position.y = newY;
		}
		
		if(Math.abs(velocity.x) < 0.000001f) velocity.x = 0;
		if(Math.abs(velocity.y) < 0.000001f) velocity.y = 0;
		
		if(velocity.x == 0 && velocity.y == 0) {
			animation.setPlaying(false);
		} else {
			animation.setPlaying(true);
		}
		
		if(Input.mouse(GLFW.GLFW_MOUSE_BUTTON_1).pressed && !swinging && !returning) {
			swinging = true;
		}
		
		if(swinging) {
			weaponOffset += 1f;
		}
		if(returning) {
			weaponOffset -= 0.5f;
		}
		
		if(weaponOffset >= 180) {
			returning = true;
			swinging = false;
		}
		
		if(weaponOffset < 0) {
			swinging = false;
			returning = false;
			weaponOffset = 0;
		}
		
		animation.update();
	}
	
	public boolean collision(float left, float right, float bottom, float top, Level level) {
		for(int y = 0; y < level.getHeight(); y++) {
			for(int x = 0; x < level.getWidth(); x++) {
				Wall w = level.wallAt(x, y);
				if(w != null) {
					float tLeft = x;
					float tRight = x + 1f;
					float tBottom = y;
					float tTop = y + 1f;
					
					boolean yOverlap = false;
					boolean xOverlap = false;
					
					if(top > tBottom && bottom < tTop) yOverlap = true;
					if(right > tLeft && left < tRight) xOverlap = true;				
					
					if(xOverlap && yOverlap) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
