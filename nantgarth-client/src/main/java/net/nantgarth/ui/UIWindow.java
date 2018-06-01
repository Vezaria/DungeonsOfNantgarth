package net.nantgarth.ui;

import net.nantgarth.Nantgarth;
import net.nantgarth.gfx.font.Font;

public class UIWindow extends UIElement {

	private int scale = 3;
	
	private int width = 80;
	private int height = 120;
	
	public UIWindow() {
		x = 100;
		y = 100;
	}
	
	public void render(UIRenderer r) {
		r.sprite(x, y, 43 * scale, 21 * scale, "windowTitleTest");
		r.sprite(x, y + (17 * scale) + (4 * scale), 4 * scale, height * scale, "windowL");
		r.sprite(x + (43 * scale), y + (17 * scale), (width - 39) * scale, 4 * scale, "windowT");
		r.sprite(x + (4 * scale) + (width * scale), y + (17 * scale), 4 * scale, 4 * scale, "windowTR");
		r.sprite(x, y + (17 * scale) + (height * scale) + (4 * scale), 4 * scale, 4 * scale, "windowBL");
		r.sprite(x + (4 * scale), y + (17 * scale) + (4 * scale), width * scale, height * scale, "windowC");
		r.sprite(x + (4 * scale) + (width * scale), y + (17 * scale) + (4 * scale), 4 * scale, height * scale, "windowR");
		r.sprite(x + (4 * scale), y + (17 * scale) + (height * scale) + (4 * scale), width * scale, 4 * scale, "windowB");
		r.sprite(x + (width * scale) + (4 * scale), y + (17 * scale) + (height * scale) + (4 * scale), 4 * scale, 4 * scale, "windowBR");
	}

	public void update(Nantgarth g, float dt) {
		
	}
}
