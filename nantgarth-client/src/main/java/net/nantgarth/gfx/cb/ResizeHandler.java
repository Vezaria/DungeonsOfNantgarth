package net.nantgarth.gfx.cb;

public interface ResizeHandler {

	/**
	 * Called whenever the window resizes.
	 * @param newWidth The width of the window after the resize.
	 * @param newHeight The height of the window after the resize.
	 */
	void onResize(int newWidth, int newHeight);
}
