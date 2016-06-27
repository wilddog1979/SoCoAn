package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexType extends AbstractTypeDeclaration {

	@XmlElement(name = "StartNode")
	List<ComplexTypeNode> startnodes;
	
	@XmlElement(name = "Node")
	List<ComplexTypeNode> nodes;

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
		for (ComplexTypeNode node : startnodes) {
			node.resolveNodeReferences(parent);
			node.resolveNodeReferences(this);
		}
		
		for (ComplexTypeNode node : nodes) {
			node.resolveNodeReferences(parent);
			node.resolveNodeReferences(this);
		}
	}
	
	public ComplexTypeNode getComplexNode(String id) throws ReferenceNotFoundException {
		ComplexTypeNode result = null;
		
		for (ComplexTypeNode startnode : startnodes) {
			if (startnode.getId().equals(id)) {
				result = startnode;
			}
		}
		
		if (result == null) {
			for (ComplexTypeNode node : nodes) {
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
		
		for (ComplexTypeNode startnode : startnodes) {
			result = recognizeNode(startnode, subcontext, sis);
			if (result) {
				break;
			}
		}

		if (result) {
			Fragment fragment = new Fragment();
			StringBuffer frgment = new StringBuffer();
			StringBuffer content = new StringBuffer();
			for (int i = 0; i < subcontext.size(); ++i) {
				Fragment subfragment = subcontext.get(i);
				if (subfragment.getFragment() != null) {
					frgment.append(subfragment.getFragment());
				}
				if (subfragment.getContent() != null) {
					content.append(subfragment.getContent());
				}
			}
			if (frgment.length() != 0) {
				fragment.setFragment(frgment.toString());
			}
			if (content.length() != 0) {
				fragment.setContent(content.toString());
			}
			context.push(fragment);
		}
		
		return result;
	}
	
	private boolean recognizeNode(ComplexTypeNode node, Context context, SourcecodeInputStream sis) throws IOException {
		boolean result = node.getTypeDeclaration().recognizeType(context, sis);
		if (result) {
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
