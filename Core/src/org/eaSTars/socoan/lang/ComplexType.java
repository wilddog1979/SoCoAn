package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
	private List<ComplexTypeNodeGroup> startnodes;
	
	@XmlElements({
		@XmlElement(name="Node", type=ComplexTypeNode.class),
		@XmlElement(name="GroupNode", type=ComplexTypeNodeGroup.class)
	})
	private List<ComplexTypeNodeGroup> nodes;

	protected Language parent;
	
	public List<ComplexTypeNodeGroup> getStartnodes() {
		if (startnodes == null) {
			startnodes = new ArrayList<ComplexTypeNodeGroup>();
		}
		return startnodes;
	}

	public List<ComplexTypeNodeGroup> getNodes() {
		if (nodes == null) {
			nodes = new ArrayList<ComplexTypeNodeGroup>();
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
		
		for (ComplexTypeNodeGroup node : getStartnodes()) {
			node.resolveNodeReferences(parent, this);
		}
		
		for (ComplexTypeNodeGroup node : getNodes()) {
			node.resolveNodeReferences(parent, this);
		}
	}
	
	private Optional<ComplexTypeNodeGroup> getNodeById(List<ComplexTypeNodeGroup> nodelist, String id) {
		return nodelist.stream().filter(node -> id.equals(node.getId())).findFirst();
	}
	
	public ComplexTypeNodeGroup getComplexNode(String id) throws ReferenceNotFoundException {
		Optional<ComplexTypeNodeGroup> result = getNodeById(getStartnodes(), id);
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
			for (ComplexTypeNodeGroup startnode : getStartnodes()) {
				result = startnode.recognizeNode(subcontext, sis);
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
	
	@Override
	public String toString() {
		return String.format("[ComplexType - ID: %s]", this.getId());
	}
}
