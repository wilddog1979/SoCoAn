package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	Literal.class,
	ComplexNode.class
})
public abstract class LanguageNode {
	
	private static final String[][] CONTROL_CHARACTERS = {
				{"\\\\n", "\n"},
				{"\\\\t", "\t"}
		};

	@XmlAttribute(name = "NodeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String nodeID;
	
	@XmlElement(name="NextNode")
	protected List<NodeRef> nextNodes;
	
	public List<NodeRef> getNextNodes() {
        if (nextNodes == null) {
        	nextNodes = new ArrayList<NodeRef>();
        }
        return nextNodes;
    }
	
	public abstract boolean recognizeNode(LanguageContext context, SourcecodeInputStream sis) throws IOException;
	
	protected LanguageContext processSubcontext(LanguageContext subcontext) {
		return subcontext;
	}
	
	public boolean recognize(LanguageContext context, SourcecodeInputStream sis) throws IOException {
		LanguageContext subcontext = context.createSubContext();
		boolean result = recognizeNode(subcontext, sis);
		
		if (result) {
			context.addAll(processSubcontext(subcontext));
			for (NodeRef nextNode : getNextNodes()) {
				result = nextNode.getRef().recognize(context, sis);
				if (result) {
					break;
				}
			}
			if (!result) {
				context.removeAll(subcontext);
				while (!subcontext.isEmpty()) {
					sis.unread(subcontext.pop().getFragment().getBytes());
				}
			}
		}
		
		return result;
	}

	public String getNodeID() {
		return nodeID;
	}

	protected String replaceControls(String value) {
		for (String[] strings : CONTROL_CHARACTERS) {
			value = value.replaceAll(strings[0], strings[1]);
		}
		return value;
	}
}
