package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	
})
public class ComplexNode extends AggregatorNode {

	@XmlElement(name="StartNode")
	protected List<NodeRef> startNodes;

	public List<NodeRef> getStartNodes() {
        if (startNodes == null) {
        	startNodes = new ArrayList<NodeRef>();
        }
        return startNodes;
    }
	
	@Override
	public boolean recognizeNode(LanguageContext context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		for (NodeRef startNode : getStartNodes()) {
			result = startNode.getRef().recognize(context, sis);
			if (result) {
				break;
			}
		}

		return result;
	}
}
