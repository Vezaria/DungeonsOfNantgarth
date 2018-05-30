package net.nantgarth.state;

import java.util.Stack;

public class StateManager {

	private static Stack<State> states = new Stack<>();
	
	public static void pushState(State state) {
		states.push(state);
	}
	
	public static void popState() {
		states.pop();
	}
	
	public static void setState(State state) {
		popState();
		pushState(state);
	}
	
	public static State currentState() {
		return states.peek();
	}
}
