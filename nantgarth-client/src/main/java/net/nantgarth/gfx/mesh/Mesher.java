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
}
