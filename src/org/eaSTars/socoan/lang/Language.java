package org.eaSTars.socoan.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language")
public class Language extends AbstractBaseElement{

	@XmlElements({
		@XmlElement(name="Include", type=Include.class),
		@XmlElement(name="LiteralType", type=LiteralType.class),
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
	
	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		this.parent = parent;
		for (AbstractBaseElement element : elements) {
			element.resolveNodeReferences(this);
			element.setProcessed(true);
		}
	}
	
	AbstractTypeDeclaration resolveTypeDeclaration(String id) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : elements) {
			if (element.isProcessed()) {
				if (element instanceof AbstractTypeDeclaration && ((AbstractTypeDeclaration)element).getId().equals(id)) {
					result = (AbstractTypeDeclaration) element;
					break;
				} else if (element instanceof Include) {
					result = ((Include)element).getInclude().resolveTypeDeclaration(id);
					if (result != null) {
						break;
					}
				}
			}
		}
		
		if (result == null && parent != null) {
			result = parent.resolveTypeDeclaration(id);
		}
		
		return result;
	}
	
	public AbstractTypeDeclaration getTypeDeclaration(String id) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : elements) {
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
