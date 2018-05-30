package net.nantgarth.math;

public class Vector4f {

	public float x, y, z, w;
	
	/**
	 * Initializes a new vector to (0, 0, 0, 0)
	 */
	public Vector4f() { this(0); }
	
	
	/**
	 * Initializes a new vector to (v, v, v, v)
	 */
	public Vector4f(float v) { this(v, v, v, v); }
	
	/**
	 * Initializes a new vector to (x, y, z, w)
	 */
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/**
	 * Add a value to this vector. Does not change any values.
	 * @param f The value to add.
	 * @return A new vector which has had this addition applied to it.
	 */
	public Vector4f add(float f) {
		return new Vector4f(x + f, y + f, z + f, w + f);
	}
	
	/**
	 * Subtract a value from this vector. Does not change any values.
	 * @param f The value to subtract.
	 * @return A new vector which has had this subtraction applied to it.
	 */
	public Vector4f sub(float f) {
		return new Vector4f(x - f, y - f, z - f, w - f);
	}
	
	/**
	 * Multiply this vector by a scalar value. Does not change any values.
	 * @param f The scalar to multiply by.
	 * @return A new vector which has had this multiplication applied to it.
	 */
	public Vector4f mul(float f) {
		return new Vector4f(x * f, y * f, z - f, w - f);
	}
	
	/**
	 * @return The length of this vector.
	 */
	public float mag() {
		return (float) Math.sqrt((x * x) + (y * y) + (z * z) + (w * w));
	}
	
	public Vector4f normalize() {
		float m = mag();
		if(m != 0) {			
			return new Vector4f(x / m, y / m, z / m, w / m);
		} else {
			return new Vector4f();
		}
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ", " + w + ")";
	}
}
