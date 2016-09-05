package org.eaSTars.socoan.lang;

import java.io.File;
import java.util.Optional;

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
		File includefilelocation = new File(location, file);
		loadReferredFile(includefilelocation);
		include.resolveFileReferences(includefilelocation.getParentFile());
	}
	
	@Override
	public void resolveFileReferences(
			Optional<FileReferenceListener> fileReferenceListener,
			File location)
					throws JAXBException {
		File includefilelocation = new File(location, file);
		loadReferredFile(includefilelocation);
		fileReferenceListener.ifPresent(
				l -> l.fileLoaded(includefilelocation, include));
		include.resolveFileReferences(
				fileReferenceListener, includefilelocation.getParentFile());
	}
	
	private void loadReferredFile(File includefilelocation)
			throws JAXBException {
		JAXBContext context =
				JAXBContext.newInstance(LanguageObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Language> doc =
			(JAXBElement<Language>) unmarshaller.unmarshal(includefilelocation);
		include = doc.getValue();
		include.setFilename(includefilelocation.getAbsolutePath());
	}
	
	public Language getInclude() {
		return include;
	}
	
	@Override
	public void resolveNodeReferences(Language parent)
			throws ReferenceNotFoundException {
		include.resolveNodeReferences(parent);
	}

	public String getFile() {
		return file;
	}
}
