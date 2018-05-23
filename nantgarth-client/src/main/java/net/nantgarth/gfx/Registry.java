package net.nantgarth.gfx;

import java.util.ArrayList;

public class Registry {

	public static final ArrayList<SheetRegister> SHEET_REGISTRY = new ArrayList<>();
	public static final ArrayList<SpriteRegister> SPRITE_REGISTRY = new ArrayList<>();
	
	static {
		registerSheet("floors", "floors.png");
		registerSheet("walls",  "walls.png");

		registerSprite("stone", "floors", 0,  0, 14, 14);
		registerSprite("grass", "floors", 14, 0, 14, 14);
		registerSprite("sand",  "floors", 28, 0, 14, 14);

		registerSprite("stonewall",        "walls", 0,  0, 14, 7);
		registerSprite("stonewallshadow",  "walls", 0,  7, 14, 7);
		registerSprite("smoothwall",       "walls", 14, 0, 14, 7);
		registerSprite("smoothwallshadow", "walls", 14, 7, 14, 7);
	}
	
	private static void registerSheet(String id, String file) {
		SHEET_REGISTRY.add(new SheetRegister(id, file));
	}
	
	private static void registerSprite(String id, String sheetID, int xPos, int yPos, int width, int height) {
		SPRITE_REGISTRY.add(new SpriteRegister(id, sheetID, xPos, yPos, width, height));
	}
	
	public static class SheetRegister {
		public final String id;
		public final String file;
		
		public SheetRegister(String id, String file) {
			this.id = id;
			this.file = file;
		}
	}
	
	public static class SpriteRegister {
		public final String id;
		public final String sheetID;
		public final int xPos, yPos;
		public final int width, height;
		
		public SpriteRegister(String id, String sheetID, int xPos, int yPos, int width, int height) {
			this.id = id;
			this.sheetID = sheetID;
			this.xPos = xPos;
			this.yPos = yPos;
			this.width = width;
			this.height = height;
		}
	}
}