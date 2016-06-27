package org.eaSTars.socoan.lang;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeNode extends AbstractTypeDeclaration{
	
	@XmlAttribute(name="type")
	private String type;
	
	protected AbstractTypeDeclaration typeDeclaration;
	
	@XmlElement(name = "NextNode")
	private List<NextNode> nextNodes;

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

	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		typeDeclaration = parent.resolveTypeDeclaration(type);
		if (typeDeclaration == null) {
			throw new ReferenceNotFoundException("type",type);
		}
	}
	
	public void resolveNodeReferences(ComplexType complexType) throws ReferenceNotFoundException {
		for (NextNode nextnode : getNextNodes()) {
			nextnode.setNode(complexType.getComplexNode(nextnode.getRef()));
		}
	}
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) {
		// TODO Auto-generated method stub
		return false;
	}
}
