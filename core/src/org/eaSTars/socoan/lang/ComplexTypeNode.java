package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeNode extends ComplexTypeInnerNode {
	
	@XmlAttribute(name="id")
	private String id;
	
	@XmlAttribute(name="type")
	private String type;
	
	@XmlTransient
	protected AbstractTypeDeclaration typeDeclaration;
	
	@XmlAttribute(name="occurrence", required=false)
	protected Occurrence occurrence = Occurrence.Single;
	
	@XmlElements({
		@XmlElement(name="NextNode", type=ComplexTypeNextNode.class),
		@XmlElement(name="Node", type=ComplexTypeNode.class)
	})
	private List<ComplexTypeInnerNode> nextNodes;

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public AbstractTypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}
	
	public List<ComplexTypeInnerNode> getNextNodes() {
		if (nextNodes == null) {
			nextNodes = new ArrayList<ComplexTypeInnerNode>();
		}
		return nextNodes;
	}

	public Occurrence getOccurrence() {
		return occurrence;
	}

	@Override
	public void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException {
		typeDeclaration = parent.resolveRecursiveTypeDeclaration(type);
		if (typeDeclaration == null) {
			throw new ReferenceNotFoundException(parent.getFilename(), "type (" + this.getId() + ")",type);
		}
		for (ComplexTypeInnerNode nextnode : getNextNodes()) {
			nextnode.resolveNodeReferences(parent, complexType);
		}
	}
	
	@Override
	public boolean recognizeNode(Context context, SourcecodeInputStream sis) throws IOException {
		int contextsize = context.size();
		boolean result = false;
		switch(occurrence) {
		case ZeroOrMore: // {}
			while(getTypeDeclaration().recognizeType(context, sis)){
				context.peek().setId(getId());
			}
			result = true;
			break;
		case ZeroOrOne: // []
			if (getTypeDeclaration().recognizeType(context, sis)) {
				context.peek().setId(getId());
			}
			result = true;
			break;
		default:
			result = getTypeDeclaration().recognizeType(context, sis);
			if (result) {
				context.peek().setId(getId());
			}
			break;
		}
		
		if (result) {
			for (ComplexTypeInnerNode nextnode : getNextNodes()) {
				result = nextnode.recognizeNode(context, sis);
				if (result) {
					break;
				}
			}
			if (!result) {
				while (context.size() > contextsize) {
					context.pop().getFragment().ifPresent(s -> sis.unread(s.getBytes()));
				}
			}
		}
		
		return result;
	}
}
