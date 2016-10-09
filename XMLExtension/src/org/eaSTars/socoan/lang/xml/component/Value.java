package org.eaSTars.socoan.lang.xml.component;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.DefaultFragmentImpl;

public class Value extends DefaultFragmentImpl {

	private String content;
	
	public Value(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
