package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.eaSTars.socoan.SourcecodeInputReader;

public class ComplexTypeNodeGroup extends ComplexTypeInnerNode {

	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "sequence", required = false)
	protected Sequence sequence = Sequence.Any;
	
	@XmlAttribute(name="occurrence", required=false)
	protected Occurrence occurrence = Occurrence.Single;

	@XmlElements({
		@XmlElement(name="NextNode", type=ComplexTypeNextNode.class),
		@XmlElement(name="Node", type=ComplexTypeNode.class),
		@XmlElement(name="GroupNode", type=ComplexTypeNodeGroup.class)
	})
	private List<ComplexTypeInnerNode> innerNodes;
	
	public String getId() {
		return id;
	}
	
	public List<ComplexTypeInnerNode> getInnerNodes() {
		if (innerNodes == null) {
			innerNodes = new ArrayList<ComplexTypeInnerNode>();
		}
		return innerNodes;
	}

	@Override
	public void resolveNodeReferences(Language parent, ComplexType complexType) throws ReferenceNotFoundException {
		for (ComplexTypeInnerNode nextnode : getInnerNodes()) {
			nextnode.resolveNodeReferences(parent, complexType);
		}
	}
	
	@Override
	public boolean recognizeNode(Context context, SourcecodeInputReader sis) throws IOException {
		int contextsize = context.size();
		boolean result = false;
		
		switch(occurrence) {
		case ZeroOrMore: // {}
			do {
				contextsize = context.size();
				result = recognizeInnerNodes(context, sis);
				if (!result) {
					while (context.size() > contextsize) {
						context.pop().getFragment().ifPresent(sis::unread);
					}
				}
			} while(result);
			
			result = true;
			break;
		case ZeroOrOne: // []
			if (!recognizeInnerNodes(context, sis)) {
				while (context.size() > contextsize) {
					context.pop().getFragment().ifPresent(sis::unread);
				}
			}
			result = true;
			break;
		default:
			result = recognizeInnerNodes(context, sis);
			if (!result) {
				while (context.size() > contextsize) {
					context.pop().getFragment().ifPresent(sis::unread);
				}
			}
			break;
		}
		
		return result;
	}
	
	public boolean recognizeInnerNodes(Context context, SourcecodeInputReader sis) throws IOException {
		boolean result = true;
		for (ComplexTypeInnerNode nextnode : getInnerNodes()) {
			result = nextnode.recognizeNode(context, sis);
			if (sequence == Sequence.Any && result) {
				break;
			} else if (sequence == Sequence.Order && !result) {
				break;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("[GroupNode - ID: %s, sequence: %s, occurrence: %s]", this.getId(), this.sequence.name(), this.occurrence.name());
	}
}
