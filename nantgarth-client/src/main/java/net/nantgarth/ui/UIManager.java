package net.nantgarth.ui;

import java.util.ArrayList;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.cb.ResizeHandler;

public class UIManager implements ResizeHandler {

	private ArrayList<UIElement> elements = new ArrayList<>();
	
	private UIRenderer renderer;
	
	public UIManager() {
		this.renderer = new UIRenderer();
	}
	
	public void render() {
		renderer.start();
		for(UIElement e : elements) {
			e.render(renderer);
		}
		renderer.end();
	}
	
	public void update(Nantgarth g, float dt) {
		for(UIElement e : elements) {
			e.update(g, dt);
		}
	}
	
	public void add(UIElement element) {
		elements.add(element);
	}
	
	public UIRenderer getRenderer() {
		return renderer;
	}

	public void onResize(int newWidth, int newHeight) {
		renderer.updateProjection(newWidth, newHeight);
		for(UIElement e : elements) {
			e.onResize(newWidth, newHeight);
		}
	}
}
