package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputReader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	ComplexType.class,
	LiteralType.class,
	TerminatedType.class,
	CustomType.class
})
public abstract class AbstractTypeDeclaration extends AbstractBaseElement {

	@XmlAttribute(name="id")
	private String id;

	public String getId() {
		return id;
	}
	
	public abstract boolean recognizeType(Context context, SourcecodeInputReader sis) throws IOException;
}
