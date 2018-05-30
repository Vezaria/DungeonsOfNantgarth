package net.nantgarth.ui;

import net.nantgarth.Nantgarth;

public class PartyHUD extends UIElement {

	private float scale = 3;
	
	public PartyHUD() {
		this.x = 2;
		this.y = 2;
	}
	
	public void render(UIRenderer r) {
		r.sprite(x, y, 21 * scale, 18 * scale, "party_portrait");
		r.sprite(x + (21 * scale), y + (3 * scale), 59 * scale, 15 * scale, "party_status");
		r.sprite(x, y + (18 * scale), 21 * scale, 3 * scale, "party_bottom");
	}

	public void update(Nantgarth g, float dt) {
		
	}
}
