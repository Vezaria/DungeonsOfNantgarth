package net.nantgarth.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import net.nantgarth.gfx.Registry.SheetRegister;
import net.nantgarth.gfx.Registry.SpriteRegister;

/**
 * The atlas generator is responsible constructing the master texture atlas.
 * It is also responsible for relocating sprites and resolving their new 
 * texture coordinates after they have been mapped onto the master atlas.
 */
public class TextureAtlas {

	/**
	 * For the time being this value is hard coded.
	 * Once we have more sprites we will need to increase this, but
	 * for now this will do.
	 */
	public static final int ATLAS_SIZE = 512;
	
	private static List<Sheet> sheets = new ArrayList<>();
	private static HashMap<String, TextureCoordinates> tcLookup = new HashMap<>();
	
	
	private static Texture texture = null;
	
	/**
	 * @param sprite The ID of the sprite to retrieve the texture coordinates of.
	 * @return The texture coordinates of the given sprite within the texture atlas.
	 */
	public static TextureCoordinates get(String sprite) {
		if(tcLookup.containsKey(sprite)) {
			return tcLookup.get(sprite);
		} else {
			throw new RuntimeException("Could not find sprite with ID: " + sprite);
		}
	}
	
	/**
	 * Parses the sheets.xml and sprites.xml files and generates the master texture atlas.
	 */
	public static void generate() {
		parseSpritesheets();
		createTexture();
		parseSprites();
	}
	
	private static void parseSprites() {
		for(SpriteRegister sr : Registry.SPRITE_REGISTRY) {
			int xPos = sr.xPos;
			int yPos = sr.yPos;
			int width = sr.width;
			int height = sr.height;
			
			Sheet sheet = getSheet(sr.sheetID);
			if(sheet == null) {
				throw new RuntimeException(sr.id + " has invalid sheet element.");
			}
			
			xPos += sheet.placedX;
			yPos += sheet.placedY;
			
		    float s1 = (float)xPos / (float)TextureAtlas.ATLAS_SIZE;
		    float t1 = (float)yPos / (float)TextureAtlas.ATLAS_SIZE;
		    
		    float s2 = (float)(xPos + width) / (float)TextureAtlas.ATLAS_SIZE;
		    float t2 = (float)(yPos + height) / (float)TextureAtlas.ATLAS_SIZE;
		
		    tcLookup.put(sr.id, new TextureCoordinates(s1, t1, s2, t2));
		}
	}
	
	private static void createTexture() {
		int[] data = new int[ATLAS_SIZE * ATLAS_SIZE];
		for(int i = 0; i < data.length; i++) {
			data[i] = 0xFFFFFFFF;
		}
		
		// This is the code for generating the atlas.
		// It is very bad, wasteful and inefficient, but it works.
		// TODO: Write a better rectangle packing algorithm.
		int currentX = 0;
		int currentY = 0;
		int nextY = 0;
		int nextX = 0;
		for(Sheet s : sheets) {
			if(currentX == 0) {
				if(s.width > ATLAS_SIZE) {
					throw new RuntimeException("Texture atlas is too small!");
				}
			}
			
			if(currentX + s.width > ATLAS_SIZE) {
				currentY = nextY;
				currentX = 0;
				nextY = currentY + s.height;
			} else {
				currentX = nextX;
				nextX = currentX + s.width;
			}
			
			if(currentX == 0) {
				nextY = currentY + s.height;
			}
			
			// Save the position of the sheet so we can use it as an offset for
			// getting the texture coordinates of sprites contained within it.
			s.setPosition(currentX, currentY);
			try {
				BufferedImage img = ImageIO.read(new File(s.path));
				for(int y = 0; y < img.getHeight(); y++) {
					for(int x = 0; x < img.getWidth(); x++) {
						int p = img.getRGB(x, y);
						
						int a = (p >> 24) & 0xFF;
						int r = (p >> 16) & 0xFF;
						int g = (p >>  8) & 0xFF;
						int b = (p >>  0) & 0xFF;
						
						int i = (x + currentX) + (y + currentY) * ATLAS_SIZE;

						data[i] = a << 24 | b << 16 | g << 8 | r;
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		texture = new Texture(data, ATLAS_SIZE, ATLAS_SIZE);
	}
	
	private static void parseSpritesheets() {	
		for(SheetRegister sr : Registry.SHEET_REGISTRY) {
			sheets.add(new Sheet(sr.id, "res/sprites/sheets/" + sr.file));
		}
		
		// Sort the sprite sheets from largest to smallest.
		sheets.sort(new Comparator<Sheet>() {
			public int compare(Sheet o1, Sheet o2) {
				if(o1.height > o2.height) return -1;
				if(o1.height < o2.height) return  1;
				return 0;
			}
		});
	}
	
	private static Sheet getSheet(String id) {
		for(Sheet s : sheets) {
			if(s.id.equals(id)) return s;
		}
		return null;
	}
	
	public static void bind() {
		texture.bind();
	}
	
	public static void unbind() {
		texture.unbind();
	}
	
	/**
	 * Represents a sprite sheet that is going to be packed into the atlas.
	 */
	public static class Sheet {
		
		private String id;
		private String path;
		
		private int width, height;
		
		/**
		 * The position of this spritesheet within the texture atlas.
		 */
		private int placedX, placedY;
		
		public Sheet(String id, String path) {
			this.id = id;
			this.path = path;
			
			// TODO: We could probably write our own PNG parser that could quickly
			//       load the file and read the header to get the width and height
			//       rather than use ImageIO to read the entire file.
			try {
				BufferedImage img = ImageIO.read(new File(path));
				this.width = img.getWidth();
				this.height = img.getHeight();
			} catch (IOException e) {
				throw new RuntimeException("Could not find sprite sheet: " + path);
			}
		}
		
		private void setPosition(int x, int y) {
			this.placedX = x;
			this.placedY = y;
		}
	}
}
