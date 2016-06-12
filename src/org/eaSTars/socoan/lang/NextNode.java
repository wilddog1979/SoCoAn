package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class NextNode extends NodeRef {
	
	public NextNode() {
	}
	
	public NextNode(LanguageNode ref) {
		super(ref);
	}
}
