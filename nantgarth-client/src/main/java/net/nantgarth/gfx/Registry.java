package net.nantgarth.gfx;

import java.util.ArrayList;

import net.nantgarth.gfx.font.Font;

public class Registry {

	public static final ArrayList<SheetRegister> SHEET_REGISTRY = new ArrayList<>();
	public static final ArrayList<SpriteRegister> SPRITE_REGISTRY = new ArrayList<>();
	
	static {
		registerSheet("floors", "floors.png");
		registerSheet("walls",  "walls.png");
		registerSheet("player", "player.png");
		registerSheet("ui", "ui.png");
		registerSheet("font_title", "font_title.png");
		registerSheet("font_text", "font_text.png");
		registerSheet("weapon", "weapon.png");

		registerSprite("weapon", "weapon", 0, 0, 30, 5);
		
		registerFont(Font.TITLE, "font_title", 140, 14);
		registerFont(Font.TEXT, "font_text", 120, 12);
		
		registerSprite("stone", "floors", 0,  0, 14, 14);
		registerSprite("grass", "floors", 14, 0, 14, 14);
		registerSprite("sand",  "floors", 28, 0, 14, 14);

		registerSprite("party_portrait", "ui", 0,  0,  21, 18);
		registerSprite("party_status",   "ui", 21, 3,  59, 15);
		registerSprite("party_bottom",   "ui", 0,  18, 21, 3);
		
		registerSprite("minimap_base", "ui", 0, 21, 80, 80);

		registerSprite("windowTR", "ui", 85, 0, 4, 4);
		registerSprite("windowBL", "ui", 80, 5, 4, 4);
		registerSprite("windowBR", "ui", 85, 5, 4, 4);
		
		registerSprite("windowT", "ui", 84, 0, 1, 4);
		registerSprite("windowB", "ui", 84, 5, 1, 4);
		registerSprite("windowL", "ui", 80, 4, 4, 1);
		registerSprite("windowR", "ui", 85, 4, 4, 1);
		registerSprite("windowC", "ui", 84, 4, 1, 1);
		
		registerSprite("windowTitleTest", "ui", 80, 9, 43, 21);
		
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
	
	private static void registerFont(Font font, String sheetID, int sheetWidth, int size) {
		font.setSize(size);
		for(char c = 33; c < 33 + 94; c++) {
			int x = (c - 33) % (sheetWidth / size);
			int y = (c - 33) / (sheetWidth / size);
			//System.out.println(c + " at " + x + ", " + y);
			registerSprite(font.getName() + "_" + c, sheetID, x * size, y * size, font.getWidth(c), size);
		}
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
