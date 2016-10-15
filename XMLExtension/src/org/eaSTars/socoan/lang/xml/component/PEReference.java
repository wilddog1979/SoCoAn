package org.eaSTars.socoan.lang.xml.component;

import org.eaSTars.socoan.lang.DefaultFragmentImpl;
import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.xml.element.IntSubset;

public class PEReference extends DefaultFragmentImpl implements IntSubset {

	private String name;
	
	public PEReference(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
