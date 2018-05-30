package net.nantgarth.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public final class Input {

	/**
	 * @param key The key to retrieve the state of. These are all defined in {@link GLFW}.
	 * @return A structure containing the state of the given key for the current frame.
	 */
	public static KeyState key(int key) {
		boolean pressed = !Keyboard.lastKeys[key] &&  Keyboard.keys[key];
		boolean released = Keyboard.lastKeys[key] && !Keyboard.keys[key];
		boolean held =     Keyboard.lastKeys[key] &&  Keyboard.keys[key];
		return new KeyState(pressed, released, held);
	}
	
	/**
	 * @param button The mouse button to retrieve the state of. These are all defined in {@link GLFW}.
	 * @return A structure containing the state of the given mouse button for the current frame.
	 */
	public static MouseState mouse(int button) {
		boolean pressed = !MouseButton.lastButtons[button] &&  MouseButton.buttons[button];
		boolean released = MouseButton.lastButtons[button] && !MouseButton.buttons[button];
		boolean held =     MouseButton.lastButtons[button] &&  MouseButton.buttons[button];
		return new MouseState(pressed, released, held, MousePosition.mx, MousePosition.my);
	}
	
	public static void update() {
		Keyboard.update();
		MouseButton.update();
	}
	
	public static int mouseX() {
		return MousePosition.mx;
	}
	
	public static int mouseY() {
		return MousePosition.my;
	}
	
	/**
	 * Wrapper for {@link GLFWCursorPosCallback}.
	 * Responsible for handling movement of the mouse.
	 */
	public static final class MousePosition extends GLFWCursorPosCallback {

		private static int mx = 0;
		private static int my = 0;
		
		public void invoke(long window, double xpos, double ypos) {
			mx = (int)xpos;
			my = (int)ypos;
		}
	}
	
	/**
	 * Wrapper for {@link GLFWMouseButtonCallback}.
	 * Responsible for handling mouse button input.
	 */
	public static final class MouseButton extends GLFWMouseButtonCallback {
		private static final int MB_COUNT = 64;
		
		private static boolean[] buttons     = new boolean[MB_COUNT];
		private static boolean[] lastButtons = new boolean[MB_COUNT];
		
		public void invoke(long window, int button, int action, int mods) {
			buttons[button] = action != GLFW.GLFW_RELEASE;
		}
		
		private static void update() {
			for(int i = 0; i < MB_COUNT; i++) {
				lastButtons[i] = buttons[i];
			}
		}
	}
	
	/**
	 * Wrapper for {@link GLFWKeyCallback}.
	 * Responsible for keyboard input.
	 */
	public static final class Keyboard extends GLFWKeyCallback {
		private static final int KEY_COUNT = Short.MAX_VALUE;
		
		private static boolean[] keys     = new boolean[KEY_COUNT];
		private static boolean[] lastKeys = new boolean[KEY_COUNT];

		public void invoke(long window, int key, int scancode, int action, int mods) {
			keys[key] = action != GLFW.GLFW_RELEASE;
		}
		
		private static void update() {
			for(int i = 0; i < KEY_COUNT; i++) {
				lastKeys[i] = keys[i];
			}
		}
	}
	
	public static final class KeyState {
		/** Was the key pressed down this frame? **/
		public final boolean pressed;
		/** Was the key released this frame? **/
		public final boolean released;
		/** Is the key being held down? (Was down last frame as well as this current frame) **/
		public final boolean held;
		
		public KeyState(boolean pressed, boolean released, boolean held) {
			this.pressed = pressed;
			this.released = released;
			this.held = held;
		}
	}
	
	public static final class MouseState {
		/** Was the button pressed down this frame? **/
		public final boolean pressed;
		/** Was the button released this frame? **/
		public final boolean released;
		/** Is the button being held down? (Was down last frame as well as this current frame) **/
		public final boolean held;
		/** The x position of the cursor **/
		public final int mx;
		/** The y position of the cursor **/
		public final int my;
		
		public MouseState(boolean pressed, boolean released, boolean held, int mx, int my) {
			this.pressed = pressed;
			this.released = released;
			this.held = held;
			this.mx = mx;
			this.my = my;
		}
	}
}
