package net.nantgarth.world;

import java.util.Random;

public class Level {

	public Floor[] floors;
	public Wall[] walls;
	
	private int width, height;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.floors = new Floor[width * height];
		this.walls = new Wall[width * height];
		
		for(int i = 0; i < floors.length; i++) {
			floors[i] = Floor.GRASS;
		}
		
		Random r = new Random();
		for(int i = 0; i < walls.length; i++) {
			int j = r.nextInt(100);
			if(j < 10) {
				walls[i] = Wall.STONE;
			} else if(j < 20) {
				walls[i] = Wall.SMOOTH;
			}
		}
	}
	
	public Wall wallAt(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return null;
		}
		return walls[x + y * width];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
