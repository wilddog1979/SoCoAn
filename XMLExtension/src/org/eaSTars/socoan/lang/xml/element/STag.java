package org.eaSTars.socoan.lang.xml.element;

import java.util.HashMap;
import java.util.Map;

import org.eaSTars.socoan.lang.FormatProvider;

public class STag extends NamedTag {

	private Map<String, String> attributes = new HashMap<String, String>();
	
	public STag(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
}
