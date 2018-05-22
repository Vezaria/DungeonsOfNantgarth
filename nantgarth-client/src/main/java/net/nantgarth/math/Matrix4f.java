package net.nantgarth.math;

public class Matrix4f {

	public float[] e = new float[4 * 4];

	/**
	 * Constructs a new identity matrix. To create a matrix with all values set to
	 * zero, use new Matrix(0);
	 */
	public Matrix4f() {
		this(1.0f);
	}

	/**
	 * Constructs a new matrix with the given value across the diagonal.
	 * @param diag
	 */
	public Matrix4f(float diag) {
		e[0 + 0 * 4] = e[1 + 1 * 4] = e[2 + 2 * 4] = e[3 + 3 * 4] = diag;
	}

	/**
	 * Adds the given matrix to this matrix.
	 * @param other The matrix to add.
	 * @return The result of the addition.
	 */
	public Matrix4f add(Matrix4f other) {
		Matrix4f res = new Matrix4f(0.0f);
		for (int i = 0; i < e.length; i++)
			res.e[i] = e[i] + other.e[i];
		return res;
	}

	/**
	 * Subtracts a given matrix from this matrix.
	 * @param other The matrix to subtract.
	 * @return The result of the subtraction.
	 */
	public Matrix4f sub(Matrix4f other) {
		Matrix4f res = new Matrix4f(0.0f);
		for (int i = 0; i < e.length; i++)
			res.e[i] = e[i] - other.e[i];
		return res;
	}

	/**
	 * Multiplies this matrix by a scalar value.
	 * @param f The value to multiply by.
	 * @return The result of the multiplication.
	 */
	public Matrix4f mul(float f) {
		Matrix4f res = new Matrix4f(0.0f);
		for (int i = 0; i < e.length; i++)
			res.e[i] = e[i] * f;
		return res;
	}

	/**
	 * Performs matrix multiplication on this matrix given another matrix.
	 * @param m The matrix with which to multiply this matrix by.
	 * @return The result of the matrix multiplication.
	 */
	public Matrix4f mul(Matrix4f m) {
		Matrix4f res = new Matrix4f(0.0f);
		for (int j = 0; j < 16; j++) {
			int i = j % 4;
			int j4 = j & 12;
			res.e[j] = e[j4 + 0] * m.e[i + 0] + 
					   e[j4 + 1] * m.e[i + 4] + 
					   e[j4 + 2] * m.e[i + 8] + 
					   e[j4 + 3] * m.e[i + 12];
		}
		return res;
	}

	/**
	 * @return A new matrix with the same values as this once, but negated.
	 */
	public Matrix4f negate() {
		Matrix4f res = new Matrix4f(0.0f);
		for (int i = 0; i < e.length; i++)
			res.e[i] = -e[i];
		return res;
	}
	
	/**
	 * Constructs an orthographic projection matrix.
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param near
	 * @param far
	 * @return Orthographic projection matrix.
	 */
	public static Matrix4f ortho(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f res = new Matrix4f(1.0f);
		
		res.e[0 + 0 * 4] = 2f  / (right - left);
		res.e[1 + 1 * 4] = 2f  / (top - bottom);
		res.e[2 + 2 * 4] = -2f / (far - near);
		res.e[3 + 0 * 4] = -((right + left) / (right - left));
		res.e[3 + 1 * 4] = -((top + bottom) / (top - bottom));
		res.e[3 + 2 * 4] = -((far + near)   / (far - near));
		
		return res;
	}
	
	/**
	 * Constructs a translation matrix.
	 * @param x The translation in the x axis.
	 * @param y The translation in the y axis.
	 * @param z The translation in the z axis.
	 * @return Translation matrix.
	 */
	public static Matrix4f translate(float x, float y, float z) {
		Matrix4f res = new Matrix4f(1.0f);

		res.e[3 + 0 * 4] = x;
		res.e[3 + 1 * 4] = y;
		res.e[3 + 2 * 4] = z;
		
		return res;
	}

	public String toString() {
		String res = "";
		for (int y = 0; y < 4; y++) {
			String row = "";
			for (int x = 0; x < 4; x++) {
				row += String.format("%f ", e[x + y * 4]);
			}
			res += row + "\n";
		}
		return res;
	}
}













