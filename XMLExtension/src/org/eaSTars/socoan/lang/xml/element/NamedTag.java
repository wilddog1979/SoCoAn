package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;

public abstract class NamedTag extends XmlElementFragment {

	private String name;
	
	public NamedTag(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
