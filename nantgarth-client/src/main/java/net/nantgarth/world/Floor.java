package net.nantgarth.world;

public enum Floor {

	GRASS("grass");
	
	public final String sprite;
	private Floor(String sprite) {
		this.sprite = sprite;
	}
}
