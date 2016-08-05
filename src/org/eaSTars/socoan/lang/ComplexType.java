package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@XmlAttribute(name="checker")
	private String checkerName;
	
	@XmlElement(name = "StartNode")
	private List<ComplexTypeNode> startnodes;
	
	@XmlElement(name = "Node")
	private List<ComplexTypeNode> nodes;

	protected Language parent;
	
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
		this.parent = parent;
		if (processorName != null) {
			ProcessorFactory processorFactory = parent.getProcessorFactory();
			if (processorFactory == null) {
				throw new ReferenceNotFoundException(parent.getFilename(), "processorFactory (" + this.getId() + ")", "processorFactory");
			}
			if (processorFactory.createProcessor(processorName) == null) {
				throw new ReferenceNotFoundException(parent.getFilename(), "processor name (" + this.getId() + ")", processorName);
			}
			if (checkerName != null && processorFactory.createChecker(checkerName) == null) {
				throw new ReferenceNotFoundException(parent.getFilename(), "checker (" + this.getId() + ")", checkerName);
			}
		} else {
			throw new ReferenceNotFoundException(parent.getFilename(), "complextype (" + this.getId() + ")", "processorName");
		}
		
		for (ComplexTypeNode node : getStartnodes()) {
			node.resolveNodeReferences(parent, this);
		}
		
		for (ComplexTypeNode node : getNodes()) {
			node.resolveNodeReferences(parent, this);
		}
	}
	
	private Optional<ComplexTypeNode> getNodeById(List<ComplexTypeNode> nodelist, String id) {
		return nodelist.stream().filter(node -> id.equals(node.getId())).findFirst();
	}
	
	public ComplexTypeNode getComplexNode(String id) throws ReferenceNotFoundException {
		Optional<ComplexTypeNode> result = getNodeById(getStartnodes(), id);
		if (!result.isPresent()) {
			result = getNodeById(getNodes(), id);
		}
		if (!result.isPresent()) {
			throw new ReferenceNotFoundException("complexnode (" + this.getId() + ")",id);
		}
		return result.get();
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

		if (subcontext.size() != 0) {
			Fragment fragment = parent.getProcessorFactory().createProcessor(processorName).apply(subcontext);
			if (fragment != null) {
				fragment.setId(this.getId());
				if (checkerName == null || (checkerName != null && !parent.getProcessorFactory().createChecker(checkerName).apply(parent, fragment))) {
					context.push(fragment);
				} else if (checkerName != null) {
					while (subcontext.size() != 0) {
						subcontext.pop().getFragment().ifPresent(s -> sis.unread(s.getBytes()));
					}
				}
			}
		}
		
		return subcontext.size() != 0;
	}
	
	protected boolean recognizeNode(ComplexTypeNode node, Context context, SourcecodeInputStream sis) throws IOException {
		int contextsize = context.size();
		boolean result = false;
		switch(node.getOccurrance()) {
		case ZeroOrMore: // {}
			while(node.getTypeDeclaration().recognizeType(context, sis)){
				context.peek().setId(node.getId());
			}
			result = true;
			break;
		case ZeroOrOne: // []
			if (node.getTypeDeclaration().recognizeType(context, sis)) {
				context.peek().setId(node.getId());
			}
			result = true;
			break;
		default:
			result = node.getTypeDeclaration().recognizeType(context, sis);
			if (result) {
				context.peek().setId(node.getId());
			}
			break;
		}
		if (result) {
			for (NextNode nextnode : node.getNextNodes()) {
				result = recognizeNode(nextnode.getNode(), context, sis);
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
