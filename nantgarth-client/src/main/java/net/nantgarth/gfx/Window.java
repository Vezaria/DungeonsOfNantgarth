package net.nantgarth.gfx;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;

import net.nantgarth.gfx.cb.ResizeHandler;
import net.nantgarth.input.Input;

public final class Window {

	private static long window = -1;

	private static GLFWKeyCallback keyCallback;
	private static GLFWMouseButtonCallback mouseButtonCallback; 
	private static GLFWCursorPosCallback cursorPosCallback;
	private static GLFWFramebufferSizeCallback framebufferSizeCallback;
	
	private static ArrayList<ResizeHandler> resizeHandlers = new ArrayList<>();
	
	private static int width, height;
	
	public static void initialize(int width, int height, String title) {
		Window.width = width;
		Window.height = height;
		
		if(!glfwInit()) {
			throw new RuntimeException("Failed to initialize GLFW.");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {
			throw new RuntimeException("Failed to create GLFW window.");
		}
		
		GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

		glfwSetKeyCallback(window, keyCallback = new Input.Keyboard());
		glfwSetMouseButtonCallback(window, mouseButtonCallback = new Input.MouseButton());
		glfwSetCursorPosCallback(window, cursorPosCallback = new Input.MousePosition());
		glfwSetFramebufferSizeCallback(window, framebufferSizeCallback = new GLFWFramebufferSizeCallback() {
			public void invoke(long window, int width, int height) {
				for(ResizeHandler h : resizeHandlers) {
					Window.width = width;
					Window.height = height;
					
					h.onResize(width, height);
				}
			}
		});
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
	}
	
	public static void addResizeHandler(ResizeHandler handler) {
		resizeHandlers.add(handler);
	}
	
	public static void update() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}
	
	public static boolean open() {
		return !glfwWindowShouldClose(window);
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
}
