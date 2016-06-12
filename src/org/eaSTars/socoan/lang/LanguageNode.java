package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.base.Literal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LanguageNode")
@XmlSeeAlso({
	ComplexNode.class,
    Literal.class
})
public abstract class LanguageNode {
	
	@XmlAttribute(name = "NodeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String nodeID;
	
	@XmlElement(name="NextNode")
	protected List<NextNode> nextNodes;
	
	public List<NextNode> getNextNodes() {
        if (nextNodes == null) {
        	nextNodes = new ArrayList<NextNode>();
        }
        return nextNodes;
    }
	
	public abstract boolean recognizeNode(Stack<LanguageFragment> context, SourcecodeInputStream sis) throws IOException;
	
	public boolean recognize(Stack<LanguageFragment> context, SourcecodeInputStream sis) throws IOException {
		Stack<LanguageFragment> subcontext = new Stack<LanguageFragment>();
		boolean result = recognizeNode(subcontext, sis);
		
		if (result) {
			context.addAll(subcontext);
			for (NextNode nextNode : getNextNodes()) {
				result = nextNode.getRef().recognize(context, sis);
				if (result) {
					break;
				}
			}
			if (!result) {
				context.removeAll(subcontext);
				while (!subcontext.isEmpty()) {
					sis.unread(subcontext.pop().getContent().getBytes());
				}
			}
		}
		
		return result;
	}
}
