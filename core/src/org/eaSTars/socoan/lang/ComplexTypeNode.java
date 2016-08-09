package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeNode extends ComplexTypeNodeGroup {
	
	@XmlAttribute(name="type")
	private String type;
	
	@XmlTransient
	protected AbstractTypeDeclaration typeDeclaration;

	public String getType() {
		return type;
	}

	public AbstractTypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	@Override
	public void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException {
		typeDeclaration = parent.resolveRecursiveTypeDeclaration(type);
		if (typeDeclaration == null) {
			throw new ReferenceNotFoundException(parent.getFilename(), "type (" + this.getId() + ")",type);
		}
		super.resolveNodeReferences(parent, complexType);
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
			result = recognizeInnerNodes(context, sis);
			if (!result) {
				while (context.size() > contextsize) {
					context.pop().getFragment().ifPresent(s -> sis.unread(s.getBytes()));
				}
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %s, type: %s, occurrence: %s", this.getId(), this.type, this.occurrence.name());
	}
}
