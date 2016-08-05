package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class NextNode {

	@XmlAttribute(name="ref")
	private String ref;
	
	protected ComplexTypeNode node;

	public String getRef() {
		return ref;
	}

	public ComplexTypeNode getNode() {
		return node;
	}

	void setNode(ComplexTypeNode node) {
		this.node = node;
	}
}
