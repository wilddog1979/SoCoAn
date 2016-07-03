package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.java.CommentType;
import org.eaSTars.socoan.lang.java.SimpleCommandType;
import org.eaSTars.socoan.lang.java.SeparatorType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	AggregatingType.class,
	CommentType.class,
	SimpleCommandType.class,
	SeparatorType.class
})
public abstract class ComplexType extends AbstractTypeDeclaration {

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
		boolean result = false;
		Context subcontext = new Context(context);
		
		for (ComplexTypeNode startnode : getStartnodes()) {
			result = recognizeNode(startnode, subcontext, sis);
			if (result) {
				break;
			}
		}

		if (result) {
			Fragment fragment = processSubcontext(subcontext);
			fragment.setId(this.getId());
			context.push(fragment);
		}
		
		return result;
	}
	
	public abstract Fragment processSubcontext(Context subcontext);
	
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
				sis.unread(context.pop().getFragment().getBytes());
			}
		}
		
		return result;
	}
}
