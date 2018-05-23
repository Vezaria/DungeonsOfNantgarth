package net.nantgarth.world;

public enum Wall {

	STONE("stonewall"),
	SMOOTH("smoothwall");
	
	public final String sprite;
	private Wall(String sprite) {
		this.sprite = sprite;
	}
}
