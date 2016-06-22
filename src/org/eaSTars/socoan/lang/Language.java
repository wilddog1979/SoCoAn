package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.base.Literal;
import org.eaSTars.socoan.lang.base.TerminatedNode;
import org.eaSTars.socoan.lang.java.SeparatorNode;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language")
public class Language {

	@XmlElements({
        @XmlElement(name = "Literal", type = Literal.class),
        @XmlElement(name = "Terminated", type = TerminatedNode.class),
        @XmlElement(name = "ComplexNode", type = ComplexNode.class),
        @XmlElement(name = "CommentNode", type = ComplexNode.class),
        @XmlElement(name = "Separator", type = SeparatorNode.class)
    })
	protected List<LanguageNode> nodeTypes;
	
	public List<LanguageNode> getNodeTypes() {
        if (nodeTypes == null) {
        	nodeTypes = new ArrayList<LanguageNode>();
        }
        return nodeTypes;
    }
	
	public boolean recognize(String id, LanguageContext context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		for (LanguageNode languageNode : getNodeTypes()) {
			if (languageNode.getNodeID().equals(id)) {
				result = languageNode.recognize(context, sis);
				if (!result) {
					while (!context.isEmpty()) {
						sis.unread(context.pop().getFragment().getBytes());
					}
				} else {
					break;
				}
			}
		}
		
		return result;
	}
}
