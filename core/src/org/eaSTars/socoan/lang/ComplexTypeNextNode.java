package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeNextNode extends ComplexTypeInnerNode {

	@XmlAttribute(name="ref")
	private String ref;
	
	protected ComplexTypeNode node;

	public String getRef() {
		return ref;
	}

	public ComplexTypeNode getNode() {
		return node;
	}

	@Override
	public void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException {
		node = complexType.getComplexNode(ref);
	}
	
	@Override
	public boolean recognizeNode(Context context, SourcecodeInputStream sis) throws IOException {
		return node.recognizeNode(context, sis);
	}
}
