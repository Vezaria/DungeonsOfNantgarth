package net.nantgarth.ui;

import java.util.ArrayList;

import net.nantgarth.Nantgarth;

public abstract class UIElement {

	/** The x position of the element in pixels from the top left of the parent element, or the screen if parent is null. */
	protected int x = 0;
	/** The y position of the element in pixels from the top left of the parent element, or the screen if parent is null. */
	protected int y = 0;
	
	protected ArrayList<UIElement> children = new ArrayList<>();
	
	protected UIElement parent = null;
	
	public abstract void render(UIRenderer r);
	public abstract void update(Nantgarth g, float dt);
	
	public void onResize(int width, int height) { }
	
	public final void add(UIElement element) {
		element.parent = this;
		children.add(element);
	}
	
	public final int getX() {
		if(parent == null) return x;
		return parent.x + x;
	}
	
	public final int getY() {
		if(parent == null) return y;
		return parent.y + y;
	}
}