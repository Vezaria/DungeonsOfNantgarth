package net.nantgarth.ui;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.Window;

public class Minimap extends UIElement {

	private int scale = 3;
	
	public Minimap() {
		calculatePosition();
	}
	
	public void render(UIRenderer r) {
		r.sprite(x, y, 80 * scale, 80 * scale, "minimap_base");
	}

	public void update(Nantgarth g, float dt) {
		
	}
	
	public void onResize(int width, int height) {
		calculatePosition();
	}
	
	private void calculatePosition() {
		x = Window.getWidth() - (82 * scale);
		y = (2 * scale);
	}
}
