package org.eaSTars.socoan.lang;

import java.util.Iterator;
import java.util.Stack;

public class LanguageContext implements Iterable<LanguageFragment> {

	private LanguageContext parent;
	
	private Stack<LanguageFragment> content = new Stack<LanguageFragment>();

	public LanguageContext(LanguageContext parent) {
		this.parent = parent;
	}
	
	public LanguageContext getParentContext() {
		return parent;
	}
	
	public LanguageContext getRootContext() {
		return parent == null ? this : parent.getRootContext();
	}
	
	public LanguageContext createSubContext() {
		return new LanguageContext(this);
	}

	public LanguageFragment push(LanguageFragment item) {
		return content.push(item);
	}

	public LanguageFragment pop() {
		return content.pop();
	}
	
	public boolean isEmpty() {
		return content.isEmpty();
	}
	
	public int size() {
		return content.size();
	}

	public LanguageFragment get(int index) {
		return content.get(index);
	}
	
	public boolean addAll(LanguageContext c) {
		return content.addAll(c.content);
	}

	public boolean removeAll(LanguageContext c) {
		return content.removeAll(c.content);
	}

	@Override
	public Iterator<LanguageFragment> iterator() {
		return content.iterator();
	}
}
