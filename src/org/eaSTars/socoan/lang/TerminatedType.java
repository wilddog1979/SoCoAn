package org.eaSTars.socoan.lang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class TerminatedType extends AbstractTypeDeclaration {

	private final static String[][] REPLACE_RULES = {
			{"(\\\\n)", "\n"},
			{"(\\\\t)", "\t"}
	};
	
	@XmlElement(name = "Terminator")
	private List<String> terminators;

	public List<String> getTerminators() {
		if (terminators == null) {
			terminators = new ArrayList<String>();
		}
		return terminators;
	}
	
	private String replaceCharacters(String value) {
		for (String[] rule : REPLACE_RULES) {
			value = value.replaceAll(rule[0], rule[1]);
		}
		
		return value;
	}
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		StringBuffer content = new StringBuffer();
		int ch = -1;
		while ((ch = sis.read()) != -1) {
			for (String terminator : terminators) {
				String fixedterminator = replaceCharacters(terminator);
				StringBuffer sb = new StringBuffer();
				if (fixedterminator.charAt(0) == (char)ch) {
					sis.unread(ch);
					for (char c : fixedterminator.toCharArray()) {
						int chr = sis.read();
						sb.append((char)chr);
						if (chr == -1) {
							result = false;
							break;
						}
						result = c == (char)chr;
						if (!result) {
							break;
						}
					}
					if (result) {
						Fragment fragment = new Fragment();
						fragment.setContent(content.toString());
						fragment.setFragment(content.toString() + fixedterminator);
						context.push(fragment);
						break;
					} else if (sb.length() != 0) {
						sis.unread(sb.toString().getBytes());
						ch = sis.read();
					}
				}
			}
			if (result) {
				break;
			} else {
				content.append((char)ch);
			}
		}
		if (!result && content.length() != 0) {
			sis.unread(content.toString().getBytes());
		}
		
		return result;
	}
}
