package org.eaSTars.socoan.lang;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Include extends AbstractBaseElement {

	@XmlAttribute(name="file")
	private String file;
	
	private Language include;

	@Override
	public void resolveFileReferences(File location) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(LanguageObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(new File(location, file));
		include = doc.getValue();
	}
	
	public Language getInclude() {
		return include;
	}
	
	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		include.resolveNodeReferences(parent);
	}
}
