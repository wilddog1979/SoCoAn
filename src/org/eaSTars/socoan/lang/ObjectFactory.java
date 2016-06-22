package org.eaSTars.socoan.lang;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.eaSTars.socoan.lang.base.Literal;

@XmlRegistry
public class ObjectFactory {

	private final static QName _SoCoAnConfig_QNAME = new QName("http://eaSTars.com/SoCoAn/lang", "Language");
	
	public ObjectFactory() {
	}
	
	public Language createLanguage() {
		return new Language();
	}
	
	public Literal createLiteral() {
		return new Literal();
	}
	
	public ComplexNode createComplexNode() {
		return new ComplexNode();
	}
	
	@XmlElementDecl(namespace = "http://eaSTars.com/SoCoAn/lang", name = "Language")
	public JAXBElement<Language> createLanguage(Language value) {
		return new JAXBElement<Language>(_SoCoAnConfig_QNAME, Language.class, null, value);
	}
}
