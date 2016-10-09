package org.eaSTars.socoan.lang.xml.element;

import java.util.ArrayList;
import java.util.List;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.xml.component.ExtID;

public class Doctypedecl extends XmlElementFragment {

	private String name;
	
	private ExtID externelId;
	
	private List<IntSubset> intSubsets = new ArrayList<IntSubset>();
	
	public Doctypedecl(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExtID getExternelId() {
		return externelId;
	}

	public void setExternelId(ExtID externelId) {
		this.externelId = externelId;
	}

	public List<IntSubset> getIntSubsets() {
		return intSubsets;
	}

}
