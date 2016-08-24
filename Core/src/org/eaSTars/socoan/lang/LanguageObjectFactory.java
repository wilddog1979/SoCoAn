package org.eaSTars.socoan.lang;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class LanguageObjectFactory {

	private final static QName _SoCoAnConfig_QNAME = new QName("http://eaSTars.com/SoCoAn/lang", "Language");
	
	public LanguageObjectFactory() {
	}
	
	@XmlElementDecl(namespace = "http://eaSTars.com/SoCoAn/lang", name = "Language")
	public JAXBElement<Language> createLanguage(Language value) {
		return new JAXBElement<Language>(_SoCoAnConfig_QNAME, Language.class, null, value);
	}
}
