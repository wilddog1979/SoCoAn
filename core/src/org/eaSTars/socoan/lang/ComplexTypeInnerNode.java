package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	ComplexTypeNode.class,
	ComplexTypeNextNode.class,
	ComplexTypeNodeGroup.class
})
public abstract class ComplexTypeInnerNode {

	public abstract void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException;
	
	public abstract boolean recognizeNode(Context context, SourcecodeInputStream sis) throws IOException;
}
