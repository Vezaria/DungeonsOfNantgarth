package net.nantgarth.ui;

import java.util.ArrayList;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Renderer;

public class UIManager {

	// construct ortho matrix where one unit is one pixel on the screen
	// ui renderer should have its own spritebatch
	
	private ArrayList<UIElement> elements = new ArrayList<>();
	
	public void render(Renderer r) {
		for(UIElement e : elements) {
			e.render(r);
		}
	}
	
	public void update(Nantgarth g, float dt) {
		for(UIElement e : elements) {
			e.update(g, dt);
		}
	}
}
