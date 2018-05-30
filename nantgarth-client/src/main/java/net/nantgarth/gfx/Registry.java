package net.nantgarth.gfx;

import java.util.ArrayList;

public class Registry {

	public static final ArrayList<SheetRegister> SHEET_REGISTRY = new ArrayList<>();
	public static final ArrayList<SpriteRegister> SPRITE_REGISTRY = new ArrayList<>();
	
	static {
		registerSheet("floors", "floors.png");
		registerSheet("walls",  "walls.png");
		registerSheet("player", "player.png");
		registerSheet("ui", "ui.png");

		registerSprite("stone", "floors", 0,  0, 14, 14);
		registerSprite("grass", "floors", 14, 0, 14, 14);
		registerSprite("sand",  "floors", 28, 0, 14, 14);

		registerSprite("party_portrait", "ui", 0,  0,  21, 18);
		registerSprite("party_status",   "ui", 21, 3,  59, 15);
		registerSprite("party_bottom",   "ui", 0,  18, 21, 3);
		
		registerSprite("player1R", "player", 0,   0, 24, 24);
		registerSprite("player2R", "player", 24,  0, 24, 24);
		registerSprite("player3R", "player", 48,  0, 24, 24);
		registerSprite("player4R", "player", 72,  0, 24, 24);
		registerSprite("player5R", "player", 96,  0, 24, 24);
		registerSprite("player6R", "player", 120, 0, 24, 24);
		
		registerSprite("player1L", "player", 0,   24, 24, 24);
		registerSprite("player2L", "player", 24,  24, 24, 24);
		registerSprite("player3L", "player", 48,  24, 24, 24);
		registerSprite("player4L", "player", 72,  24, 24, 24);
		registerSprite("player5L", "player", 96,  24, 24, 24);
		registerSprite("player6L", "player", 120, 24, 24, 24);
		
		registerWall("stonewall",  "walls", 0,  0);
		registerWall("smoothwall", "walls", 16, 0);
	}
	
	private static void registerWall(String id, String sheetID, int xPos, int yPos) {
		registerSprite(id,  sheetID, xPos, yPos, 16, 8);
		registerSprite(id + "_shadow", sheetID, xPos,     yPos + 8,  16, 8);
		registerSprite(id + "_top",    sheetID, xPos,     yPos + 16, 16, 16);
		registerSprite(id + "_dleft",  sheetID, xPos,     yPos + 32, 2,  20);
		registerSprite(id + "_dright", sheetID, xPos + 3, yPos + 32, 2,  20);
		registerSprite(id + "_dtop",   sheetID, xPos,     yPos + 52, 16, 2);
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
