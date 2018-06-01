package net.nantgarth.gfx.mesh;

import java.nio.FloatBuffer;

import net.nantgarth.gfx.TextureCoordinates;
import net.nantgarth.math.Vector2f;

public interface Mesher {
	
	public static final float TILE_SIZE = 1;

	public static final Mesher FLOOR = (v, p, tc) -> {
	    float x1 = p.x;
	    float y1 = p.y;
	    float x2 = p.x + TILE_SIZE;
	    float y2 = p.y + TILE_SIZE;
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top left
	    v.put(x1);
	    v.put(y2);
	    v.put(tc.s1);
	    v.put(tc.t1);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom right
	    v.put(x2);
	    v.put(y1);
	    v.put(tc.s2);
	    v.put(tc.t2);
	    
	    return 6;
	};
	
	public static final Mesher WALL = (v, p, tc) -> {
	    float x1 = p.x;
	    float y1 = p.y;
	    float x2 = p.x + TILE_SIZE;
	    float y2 = p.y + TILE_SIZE / 2f;
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top left
	    v.put(x1);
	    v.put(y2);
	    v.put(tc.s1);
	    v.put(tc.t1);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom right
	    v.put(x2);
	    v.put(y1);
	    v.put(tc.s2);
	    v.put(tc.t2);
	    
	    return 6;
	};
	
	public static final Mesher DETAIL_TOP = (v, p, tc) -> {
	    float x1 = p.x;
	    float y1 = p.y;
	    float x2 = p.x + TILE_SIZE;
	    float y2 = p.y + (TILE_SIZE / 8f);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top left
	    v.put(x1);
	    v.put(y2);
	    v.put(tc.s1);
	    v.put(tc.t1);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom right
	    v.put(x2);
	    v.put(y1);
	    v.put(tc.s2);
	    v.put(tc.t2);
	    
	    return 6;
	};
	
	public static final Mesher DETAIL_SIDE = (v, p, tc) -> {
	    float x1 = p.x;
	    float y1 = p.y;
	    float x2 = p.x + (TILE_SIZE / 8f);
	    float y2 = p.y + TILE_SIZE + (TILE_SIZE / 4f);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top left
	    v.put(x1);
	    v.put(y2);
	    v.put(tc.s1);
	    v.put(tc.t1);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom left
	    v.put(x1);
	    v.put(y1);
	    v.put(tc.s1);
	    v.put(tc.t2);

	    // Top right
	    v.put(x2);
	    v.put(y2);
	    v.put(tc.s2);
	    v.put(tc.t1);
	    
	    // Bottom right
	    v.put(x2);
	    v.put(y1);
	    v.put(tc.s2);
	    v.put(tc.t2);
	    
	    return 6;
	};

	public int generate(FloatBuffer v, Vector2f p, TextureCoordinates tc);
	
	public static Vector2f rotate(float x, float y, float rx, float ry, float r) {
		float px = rx;
		float py = ry;
		
		float nx = (float) (px + (x-px)*Math.cos(r)-(y-py)*Math.sin(r));
		float ny = (float) (py + (x-px)*Math.sin(r)+(y-py)*Math.cos(r));
		
		return new Vector2f(nx, ny);
	}
	
	public static Mesher create(float width, float height, float r, float rotationX, float rotationY) {
		return new Mesher() {
			public int generate(FloatBuffer v, Vector2f p, TextureCoordinates tc) {
			    float x1 = p.x;
			    float y1 = p.y;
			    float x2 = p.x + width;
			    float y2 = p.y + height;
			    
			    float rx = x1 + rotationX;
			    float ry = y1 + rotationY;
			    
			    Vector2f rot = rotate(x1, y1, rx, ry, r);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t2);

			    rot = rotate(x1, y2, rx, ry, r);
			    // Top left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t1);

			    rot = rotate(x2, y2, rx, ry, r);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t1);

			    rot = rotate(x1, y1, rx, ry, r);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t2);

			    rot = rotate(x2, y2, rx, ry, r);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t1);
			    
			    rot = rotate(x2, y1, rx, ry, r);
			    // Bottom right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t2);
			    
			    return 6;
			}
		};
	}

	public static Mesher createFlipped(float width, float height, float r) {
		return new Mesher() {
			public int generate(FloatBuffer v, Vector2f p, TextureCoordinates tc) {
			    float x1 = p.x;
			    float y1 = p.y;
			    float x2 = p.x + width;
			    float y2 = p.y + height;
			    
			    float rx = x1 + 1;
			    float ry = y1 + 1;
			    
			    Vector2f rot = rotate(x1, y1, rx, ry, r);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t1);

			    rot = rotate(x1, y2, rx, ry, r);
			    // Top left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t2);

			    rot = rotate(x2, y2, rx, ry, r);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t2);

			    rot = rotate(x1, y1, rx, ry, r);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t1);

			    rot = rotate(x2, y2, rx, ry, r);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t2);
			    
			    rot = rotate(x2, y1, rx, ry, r);
			    // Bottom right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t1);
			    
			    return 6;
			}
		};
	}
	
	public static Mesher createFlipped(float width, float height, int type, float r, float g, float b) {
		return new Mesher() {
			public int generate(FloatBuffer v, Vector2f p, TextureCoordinates tc) {
			    //TODO: This could be used to create a gradient of text if we set 
			    //      the bottom vertices and the top vertices to different colors.
			    
			    float x1 = p.x;
			    float y1 = p.y;
			    float x2 = p.x + width;
			    float y2 = p.y + height;
			    
			    float rx = x1 + 1;
			    float ry = y1 + 1;
			    
			    float rotation = 0;
			    Vector2f rot = rotate(x1, y1, rx, ry, rotation);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t1);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);

			    rot = rotate(x1, y2, rx, ry, rotation);
			    // Top left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t2);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);

			    rot = rotate(x2, y2, rx, ry, rotation);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t2);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);

			    rot = rotate(x1, y1, rx, ry, rotation);
			    // Bottom left
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s1);
			    v.put(tc.t1);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);

			    rot = rotate(x2, y2, rx, ry, rotation);
			    // Top right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t2);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);
			    
			    rot = rotate(x2, y1, rx, ry, rotation);
			    // Bottom right
			    v.put(rot.x);
			    v.put(rot.y);
			    v.put(tc.s2);
			    v.put(tc.t1);
			    v.put(r);
			    v.put(g);
			    v.put(b);
			    v.put(type);
			    
			    return 6;
			}
		};
	}
}
