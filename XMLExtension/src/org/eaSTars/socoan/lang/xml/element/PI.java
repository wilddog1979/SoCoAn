package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;

public class PI extends XmlElementFragment implements IntSubset, Content {

	private String target;
	
	private String content;
	
	public PI(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
