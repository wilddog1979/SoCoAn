package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;

public class CDSect extends XmlElementFragment implements Content {

	private String data;
	
	public CDSect(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
