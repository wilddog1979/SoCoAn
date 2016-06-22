package org.eaSTars.socoan.lang.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AggregatorNode;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.NodeRef;

@XmlAccessorType(XmlAccessType.FIELD)
public class SeparatorNode extends AggregatorNode {

	@XmlElement(name="Entry")
	protected List<NodeRef> entries;

	public List<NodeRef> getEntries() {
        if (entries == null) {
        	entries = new ArrayList<NodeRef>();
        }
        return entries;
    }

	@Override
	public boolean recognizeNode(LanguageContext context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		boolean partial = false;
		do {
			for (NodeRef entry : getEntries()) {
				partial = entry.getRef().recognize(context, sis);
				result |= partial;
				if (partial) {
					break;
				}
			}
		} while (partial);
		
		return result;
	}
}
