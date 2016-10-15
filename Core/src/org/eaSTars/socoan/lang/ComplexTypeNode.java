package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.eaSTars.socoan.SourcecodeInputReader;

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
	public boolean recognizeNode(Context context, SourcecodeInputReader sis) throws IOException {
		int contextsize = context.size();
		boolean result = false;
		switch(occurrence) {
		case ZeroOrMore: // {}
			int contextsize2 = contextsize;
			while(getTypeDeclaration().recognizeType(context, sis)){
				if (getId() != null && contextsize2 + 1 == context.size()) {
					context.peek().setId(getId());
				}
				contextsize2 = context.size();
			}
			result = true;
			break;
		case ZeroOrOne: // []
			if (getTypeDeclaration().recognizeType(context, sis)) {
				if (getId() != null && contextsize + 1 == context.size()) {
					context.peek().setId(getId());
				}
			}
			result = true;
			break;
		default:
			result = getTypeDeclaration().recognizeType(context, sis);
			if (result && getId() != null && contextsize + 1 == context.size()) {
				context.peek().setId(getId());
			}
			break;
		}
		
		if (result) {
			result = recognizeInnerNodes(context, sis);
			if (!result) {
				while (context.size() > contextsize) {
					context.pop().getFragment().ifPresent(sis::unread);
				}
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("[Node - ID: %s, type: %s, occurrence: %s]", this.getId(), this.type, this.occurrence.name());
	}
}
