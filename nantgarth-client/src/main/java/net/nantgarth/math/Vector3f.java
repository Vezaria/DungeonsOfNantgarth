package net.nantgarth.math;

public class Vector3f {

	public float x, y, z;
	
	/**
	 * Initializes a new vector to (0, 0, 0)
	 */
	public Vector3f() { this(0); }
	
	
	/**
	 * Initializes a new vector to (v, v, v)
	 */
	public Vector3f(float v) { this(v, v, v); }
	
	/**
	 * Initializes a new vector to (x, y, z)
	 */
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Add a value to this vector. Does not change any values.
	 * @param f The value to add.
	 * @return A new vector which has had this addition applied to it.
	 */
	public Vector3f add(float f) {
		return new Vector3f(x + f, y + f, z + f);
	}
	
	/**
	 * Subtract a value from this vector. Does not change any values.
	 * @param f The value to subtract.
	 * @return A new vector which has had this subtraction applied to it.
	 */
	public Vector3f sub(float f) {
		return new Vector3f(x - f, y - f, z - f);
	}
	
	/**
	 * Multiply this vector by a scalar value. Does not change any values.
	 * @param f The scalar to multiply by.
	 * @return A new vector which has had this multiplication applied to it.
	 */
	public Vector3f mul(float f) {
		return new Vector3f(x * f, y * f, z - f);
	}
	
	/**
	 * @return The length of this vector.
	 */
	public float mag() {
		return (float) Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public Vector3f normalize() {
		float m = mag();
		if(m != 0) {			
			return new Vector3f(x / m, y / m, z / m);
		} else {
			return new Vector3f();
		}
	}
	
	/**
	 * Calculates the dot product of this vector with another.
	 * @param v Other vector.
	 * @return The result of this vector dotted with the other.
	 */
	public float dot(Vector3f v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	/**
	 * Calculates the cross product of this vector with another.
	 * The cross product is defined as the vector which is at a 90 degree angle
	 * to two given vectors.
	 * @param v Other vector.
	 * @return The cross product of this vector and v
	 */
	public Vector3f cross(Vector3f v) {
		return new Vector3f(y * v.z - z * v.y,
							z * v.x - x * v.z, 
							x * v.y - y * v.x);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}
