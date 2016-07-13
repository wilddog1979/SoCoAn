package org.eaSTars.socoan.lang;

import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Stream;

public class Context implements Iterable<Fragment>{

	private FormatProvider formatProvider;
	
	private Context parent = null;
	
	private Stack<Fragment> context = new Stack<Fragment>();

	public Context(FormatProvider formatProvider) {
		this.formatProvider = formatProvider;
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

	@Override
	public Iterator<Fragment> iterator() {
		return context.iterator();
	}
	
	public Stream<Fragment> stream() {
		return context.stream();
	}
	
	public FormatProvider getFormatProvider() {
		FormatProvider formatProvider = this.formatProvider;
		
		if (formatProvider == null && parent != null) {
			formatProvider = parent.getFormatProvider();
		}
		
		return formatProvider;
	}
}
