package net.nantgarth.game;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Renderer;
import net.nantgarth.math.Vector2f;

public abstract class GameObject {

	protected Vector2f position = new Vector2f();
	
	public abstract void render(Nantgarth g, Renderer r);
	public abstract void update(Nantgarth g, float dt);
	
	public Vector2f getPosition() {
		return position;
	}
}
