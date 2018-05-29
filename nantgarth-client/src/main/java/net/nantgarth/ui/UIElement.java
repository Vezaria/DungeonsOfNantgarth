package net.nantgarth.ui;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Renderer;

public abstract class UIElement {

	/** The x position of the element in pixels from the top left of the screen. */
	protected int x = 0;
	/** The y position of the element in pixels from the top left of the screen. */
	protected int y = 0;
	
	public abstract void render(Renderer r);
	public abstract void update(Nantgarth g, float dt);
}
