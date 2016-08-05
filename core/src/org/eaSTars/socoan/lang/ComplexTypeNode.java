package org.eaSTars.socoan.lang;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeNode {
	
	@XmlAttribute(name="id")
	private String id;
	
	@XmlAttribute(name="type")
	private String type;
	
	@XmlTransient
	protected AbstractTypeDeclaration typeDeclaration;
	
	@XmlAttribute(name="occurrence", required=false)
	protected Occurrence occurrence = Occurrence.Single;
	
	@XmlElement(name = "NextNode")
	private List<NextNode> nextNodes;

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public AbstractTypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}
	
	public List<NextNode> getNextNodes() {
		if (nextNodes == null) {
			nextNodes = new ArrayList<NextNode>();
		}
		return nextNodes;
	}

	public Occurrence getOccurrence() {
		return occurrence;
	}

	public void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException {
		//typeDeclaration = parent.resolveTypeDeclaration(type, true);
		typeDeclaration = parent.resolveRecursiveTypeDeclaration(type);
		if (typeDeclaration == null) {
			throw new ReferenceNotFoundException(parent.getFilename(), "type (" + this.getId() + ")",type);
		}
		for (NextNode nextnode : getNextNodes()) {
			nextnode.setNode(complexType.getComplexNode(nextnode.getRef()));
		}
	}
}
