package net.nantgarth.state;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Renderer;

public abstract class State {

	public abstract void render(Nantgarth g, Renderer renderer);
	public abstract void update(Nantgarth g, float dt);
	
	public void postRender(Nantgarth nantgarth) {
		
	}
}
