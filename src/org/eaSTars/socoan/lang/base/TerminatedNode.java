package org.eaSTars.socoan.lang.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageNode;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Terminated")
public class TerminatedNode extends LanguageNode {

	@XmlElement(name = "Terminator")
	protected List<String> terminators;
	
	@Override
	public boolean recognizeNode(LanguageContext context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		StringBuffer literal = new StringBuffer();
		int ch = -1;
		while((ch = sis.read()) != -1) {
			sis.unread(ch);
			for (String terminator : getTerminators()) {
				terminator = replaceControls(terminator);
				
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < terminator.length(); ++i) {
					ch = sis.read();
					if (ch == -1) {
						result = false;
					} else {
						sb.append((char)ch);
						result = terminator.charAt(i) == (char)ch;
					}
					if (!result) {
						break;
					}
				}
				if (result) {
					LiteralFragment literalFragment = new LiteralFragment();
					literalFragment.setContent(literal.toString());
					literalFragment.setFragment(literal.append(sb).toString());
					context.push(literalFragment);
					break;
				} else if (sb.length() != 0) {
					sis.unread(sb.toString().getBytes());
				}
			}
			if (result) {
				break;
			} else {
				literal.append((char)sis.read());
			}
		}
		if (!result && literal.length() != 0) {
			sis.unread(literal.toString().getBytes());
		}
		
		return result;
	}

	public List<String> getTerminators() {
		if (terminators == null) {
			terminators = new ArrayList<String>();
		}
		return terminators;
	}

}
