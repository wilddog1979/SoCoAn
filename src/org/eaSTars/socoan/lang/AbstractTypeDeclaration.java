package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	LiteralType.class,
	TerminatedType.class,
	ComplexType.class,
	ComplexTypeNode.class
})
public abstract class AbstractTypeDeclaration extends AbstractBaseElement {

	@XmlAttribute(name="id")
	private String id;

	public String getId() {
		return id;
	}
	
	public abstract boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException;
}
