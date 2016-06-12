package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexNode")
public class ComplexNode extends LanguageNode {

	@XmlElement(name="StartNode")
	protected List<StartNode> startNodes;
	
	@XmlElement(name="EndNode")
	protected List<EndNode> endNodes;

	public List<StartNode> getStartNodes() {
        if (startNodes == null) {
        	startNodes = new ArrayList<StartNode>();
        }
        return startNodes;
    }
	
	public List<EndNode> getEndNodes() {
        if (endNodes == null) {
        	endNodes = new ArrayList<EndNode>();
        }
        return endNodes;
    }
	
	@Override
	public boolean recognizeNode(Stack<LanguageFragment> context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		for (StartNode startNode : startNodes) {
			result = startNode.getRef().recognize(context, sis);
			if (result) {
				break;
			}
		}
		
		return result;
	}
}
