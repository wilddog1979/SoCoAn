package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.xml.component.ExtID;

public class NotationDecl extends XmlElementFragment implements IntSubset {

	private String name;
	
	private ExtID externalId;
	
	public NotationDecl(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExtID getExternalId() {
		return externalId;
	}

	public void setExternalId(ExtID externalId) {
		this.externalId = externalId;
	}

}
