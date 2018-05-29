package net.nantgarth.math;

public class Vector2f {

	public float x, y;
	
	/**
	 * Initializes a new vector to (0, 0)
	 */
	public Vector2f() { this(0); }
	
	/**
	 * Initializes a new vector to (v, v)
	 */
	public Vector2f(float v) { this(v, v); }
	
	/**
	 * Initializes a new vector to (x, y)
	 */
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Add a value to this vector. Does not change any values.
	 * @param f The value to add.
	 * @return A new vector which has had this addition applied to it.
	 */
	public Vector2f add(float f) {
		return new Vector2f(x + f, y + f);
	}
	
	/**
	 * Subtract a value from this vector. Does not change any values.
	 * @param f The value to subtract.
	 * @return A new vector which has had this subtraction applied to it.
	 */
	public Vector2f sub(float f) {
		return new Vector2f(x - f, y - f);
	}
	
	/**
	 * Multiply this vector by a scalar value. Does not change any values.
	 * @param f The scalar to multiply by.
	 * @return A new vector which has had this multiplication applied to it.
	 */
	public Vector2f mul(float f) {
		return new Vector2f(x * f, y * f);
	}
	
	/**
	 * @return The length of this vector.
	 */
	public float mag() {
		return (float) Math.sqrt((x * x) + (y * y));
	}
	
	public Vector2f normalize() {
		float m = mag();
		if(m != 0) {			
			return new Vector2f(x / m, y / m);
		} else {
			return new Vector2f();
		}
	}
	
	/**
	 * Calculates the dot product of this vector with another.
	 * @param v Other vector.
	 * @return The result of this vector dotted with the other.
	 */
	public float dot(Vector2f v) {
		return x * v.x + y * v.y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
