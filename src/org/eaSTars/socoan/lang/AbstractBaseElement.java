package org.eaSTars.socoan.lang;

import java.io.File;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	AbstractTypeDeclaration.class,
	Include.class
})
public abstract class AbstractBaseElement {

	private boolean processed = false;
	
	public void resolveFileReferences(File location) throws JAXBException {
		
	}
	
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
