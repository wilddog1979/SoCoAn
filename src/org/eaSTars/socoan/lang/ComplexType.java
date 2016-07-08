package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	
})
public class ComplexType extends AbstractTypeDeclaration {

	@XmlAttribute(name="multiple")
	private boolean multiple = false;
	
	@XmlAttribute(name="processor")
	private String processorName;
	
	protected SubcontextProcessor processor;

	@XmlElement(name = "StartNode")
	private List<ComplexTypeNode> startnodes;
	
	@XmlElement(name = "Node")
	private List<ComplexTypeNode> nodes;

	public List<ComplexTypeNode> getStartnodes() {
		if (startnodes == null) {
			startnodes = new ArrayList<ComplexTypeNode>();
		}
		return startnodes;
	}

	public List<ComplexTypeNode> getNodes() {
		if (nodes == null) {
			nodes = new ArrayList<ComplexTypeNode>();
		}
		return nodes;
	}

	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		if (processorName != null) {
			ProcessorFactory processorFactory = parent.getProcessorFactory();
			if (processorFactory == null) {
				throw new ReferenceNotFoundException("processorFactory", "processorFactory");
			}
			processor = processorFactory.createProcessor(processorName);
			if (processor == null) {
				throw new ReferenceNotFoundException("processor name", processorName);
			}
		} else {
			throw new ReferenceNotFoundException("complextype", "processorName");
		}
		
		for (ComplexTypeNode node : getStartnodes()) {
			node.resolveNodeReferences(parent, this);
		}
		
		for (ComplexTypeNode node : getNodes()) {
			node.resolveNodeReferences(parent, this);
		}
	}
	
	public ComplexTypeNode getComplexNode(String id) throws ReferenceNotFoundException {
		ComplexTypeNode result = null;
		
		for (ComplexTypeNode startnode : getStartnodes()) {
			if (startnode.getId().equals(id)) {
				result = startnode;
			}
		}
		
		if (result == null) {
			for (ComplexTypeNode node : getNodes()) {
				if (node.getId().equals(id)) {
					result = node;
				}
			}
		}
		
		if (result == null) {
			throw new ReferenceNotFoundException("complexnode",id);
		}
		
		return result;
	}
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException {
		Context subcontext = new Context(context);
		
		boolean result = false;
		do {
			for (ComplexTypeNode startnode : getStartnodes()) {
				result = recognizeNode(startnode, subcontext, sis);
				if (result) {
					break;
				}
			}
		} while (multiple && result);

		if (subcontext.size() != 0 && processor != null) {
			Fragment fragment = processor.processSubcontext(subcontext);
			fragment.setId(this.getId());
			context.push(fragment);
		}
		
		return subcontext.size() != 0;
	}
	
	protected boolean recognizeNode(ComplexTypeNode node, Context context, SourcecodeInputStream sis) throws IOException {
		boolean result = node.getTypeDeclaration().recognizeType(context, sis);
		if (result) {
			context.peek().setId(node.getId());
			for (NextNode nextnode : node.getNextNodes()) {
				result = recognizeNode(nextnode.getNode(), context, sis);
				if (result) {
					break;
				}
			}
			if (!result) {
				context.pop().getFragment().ifPresent(s -> sis.unread(s.getBytes()));
			}
		}
		
		return result;
	}
}
