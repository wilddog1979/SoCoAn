package org.eaSTars.socoan.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language")
public class Language extends AbstractBaseElement implements FormatProvider{

	@XmlTransient
	private String filename;
	
	@XmlAttribute(name="languageprocessorfactory")
	protected String processorfactoryname;
	
	protected ProcessorFactory processorFactory = null;
	
	@XmlElement(name="FormatMasks")
	@XmlJavaTypeAdapter(FormatEntriesAdapter.class)
	private Map<String, String> formatmasks;
	
	@XmlElements({
		@XmlElement(name="Include", type=Include.class),
		@XmlElement(name="LiteralType", type=LiteralType.class),
		@XmlElement(name="KeywordType", type=KeywordType.class),
		@XmlElement(name="TerminatedType", type=TerminatedType.class),
		@XmlElement(name="ComplexType", type=ComplexType.class),
		@XmlElement(name="CustomType", type=CustomType.class)
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
	
	public Language getRoot() {
		return parent == null ? this : parent.getRoot();
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
		
		if (processorfactoryname != null) {
			try {
				processorFactory = Class.forName(processorfactoryname).asSubclass(ProcessorFactory.class).newInstance();
			} catch (Exception e) {
				throw new ReferenceNotFoundException(parent.getFilename(), "processorfactory", processorfactoryname);
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
			result = parent.resolveTypeDeclaration(id, upward);
		}
		
		return result;
	}
	
	public AbstractTypeDeclaration resolveRecursiveTypeDeclaration(String id) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : getElements()) {
			if (element instanceof AbstractTypeDeclaration && ((AbstractTypeDeclaration)element).getId().equals(id)) {
				result = (AbstractTypeDeclaration) element;
				break;
			}
		}
		
		return result != null ? result : resolveTypeDeclaration(id, true);
	}
	
	private AbstractTypeDeclaration getTypeDeclaration(Language origin, String id, boolean upward) {
		AbstractTypeDeclaration result = null;
		
		for (AbstractBaseElement element : getElements()) {
			if (element instanceof AbstractTypeDeclaration && ((AbstractTypeDeclaration)element).getId().equals(id)) {
				result = (AbstractTypeDeclaration) element;
				break;
			} else if (element instanceof Include) {
				Include include = (Include) element;
				if (include.getInclude() == origin) {
					break;
				}
				result = include.getInclude().getTypeDeclaration(id);
				if (result != null) {
					break;
				}
			}
		}
		
		return result;
	}
	
	public AbstractTypeDeclaration getTypeDeclaration(String id) {
		return getTypeDeclaration(null, id, true);
	}
	
	private Optional<String> getFormat(Language origin, String maskid, boolean upward) {
		Optional<String> result = Optional.empty();
		
		if (formatmasks != null && formatmasks.containsKey(maskid)) {
			result = Optional.ofNullable(replaceCharacters(formatmasks.get(maskid)));
		}
		
		if (!result.isPresent()) {
			for (AbstractBaseElement element : getElements()) {
				if (element instanceof Include) {
					Include include = (Include) element;
					if (include.getInclude() == origin) {
						break;
					}
					result = include.getInclude().getFormat(null, maskid, false);
					if (result.isPresent()) {
						break;
					}
				}
			}
		}
		
		if (upward && !result.isPresent() && parent != null) {
			result = parent.getFormat(this, maskid, upward);
		}
		
		return result;
	}
	
	@Override
	public Optional<String> getFormat(String maskid) {
		return getFormat(null, maskid, true);
	}
	
	@Override
	public String getFormat(String key, String defaultvalue) {
		Optional<String> result = getFormat(key);
		return result.isPresent() ? result.get() : defaultvalue;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
