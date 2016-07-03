package org.eaSTars.socoan.lang;

import java.util.Stack;

public class Context {

	private Context parent = null;
	
	private Stack<Fragment> context = new Stack<Fragment>();

	public Context() {
	}
	
	public Context(Context parent) {
		this.parent = parent;
	}

	public Fragment push(Fragment item) {
		return context.push(item);
	}

	public Fragment pop() {
		return context.pop();
	}
	
	public Fragment peek() {
		return context.peek();
	}

	public int size() {
		return context.size();
	}

	public Fragment get(int index) {
		return context.get(index);
	}
}
