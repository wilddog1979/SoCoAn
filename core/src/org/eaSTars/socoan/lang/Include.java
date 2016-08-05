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
		File includefilelocation = new File(location, file);
		@SuppressWarnings("unchecked")
		JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(includefilelocation);
		include = doc.getValue();
		include.setFilename(includefilelocation.getAbsolutePath());
		
		include.resolveFileReferences(includefilelocation.getParentFile());
	}
	
	public Language getInclude() {
		return include;
	}
	
	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		include.resolveNodeReferences(parent);
	}

	public String getFile() {
		return file;
	}
}
