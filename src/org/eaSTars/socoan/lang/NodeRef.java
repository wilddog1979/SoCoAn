package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	
})
public class NodeRef {

	@XmlAttribute(name = "ref")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected LanguageNode ref;

	public NodeRef() {
	}
	
	public NodeRef(LanguageNode ref) {
		this.ref = ref;
	}
	
	public LanguageNode getRef() {
		return ref;
	}

	public void setRef(LanguageNode ref) {
		this.ref = ref;
	}
}
