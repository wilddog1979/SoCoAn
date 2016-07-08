package org.eaSTars.socoan.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.lang.java.KeywordType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language")
public class Language extends AbstractBaseElement{

	@XmlAttribute(name="processorfactory")
	private String factoryname;
	
	private ProcessorFactory processorFactory = null;
	
	@XmlElements({
		@XmlElement(name="Include", type=Include.class),
		@XmlElement(name="LiteralType", type=LiteralType.class),
		@XmlElement(name="KeywordType", type=KeywordType.class),
		@XmlElement(name="TerminatedType", type=TerminatedType.class),
		@XmlElement(name="ComplexType", type=ComplexType.class)
	})
	private List<AbstractBaseElement> elements;
	
	private Language parent;
	
	public List<AbstractBaseElement> getElements() {
		if (elements == null) {
			elements = new ArrayList<AbstractBaseElement>();
		}
		return elements;
	}
	
	public Language getParent() {
		return parent;
	}

	@Override
	public void resolveFileReferences(File location) throws JAXBException {
		for (AbstractBaseElement element : getElements()) {
			element.resolveFileReferences(location);
		}
	}
	
	public ProcessorFactory getProcessorFactory() {
		ProcessorFactory result = processorFactory;
		
		if (result == null && parent != null) {
			result = parent.getProcessorFactory();
		}
		
		return result;
	}
	
	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		this.parent = parent;
		
		if (factoryname != null) {
			try {
				processorFactory = Class.forName(factoryname).asSubclass(ProcessorFactory.class).newInstance();
			} catch (Exception e) {
				throw new ReferenceNotFoundException("processorfactory", factoryname);
			}
		}
		
		for (AbstractBaseElement element : getElements()) {
			element.resolveNodeReferences(this);
			element.setProcessed(true);
		}
	}
	
	AbstractTypeDeclaration resolveTypeDeclaration(String id, boolean upward) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : getElements()) {
			if (element.isProcessed()) {
				if (element instanceof AbstractTypeDeclaration && ((AbstractTypeDeclaration)element).getId().equals(id)) {
					result = (AbstractTypeDeclaration) element;
					break;
				} else if (element instanceof Include) {
					result = ((Include)element).getInclude().resolveTypeDeclaration(id, false);
					if (result != null) {
						break;
					}
				}
			}
		}
		
		if (upward && result == null && parent != null) {
			result = parent.resolveTypeDeclaration(id, true);
		}
		
		return result;
	}
	
	public AbstractTypeDeclaration getTypeDeclaration(String id) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : getElements()) {
			if (element instanceof AbstractTypeDeclaration && ((AbstractTypeDeclaration)element).getId().equals(id)) {
				result = (AbstractTypeDeclaration) element;
				break;
			} else if (element instanceof Include) {
				result = ((Include)element).getInclude().getTypeDeclaration(id);
				if (result != null) {
					break;
				}
			}
		}
		
		return result;
	}
}
