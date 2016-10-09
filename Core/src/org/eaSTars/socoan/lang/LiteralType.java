package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.eaSTars.socoan.SourcecodeInputReader;

@XmlAccessorType(XmlAccessType.FIELD)
public class LiteralType extends AbstractTypeDeclaration {

	@XmlAttribute(name="literal")
	protected String literal;
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputReader sis) throws IOException {
		boolean result = false;
		
		StringBuffer sb = new StringBuffer();
		String fixedliteral = replaceCharacters(literal);
		for (char c : fixedliteral.toCharArray()) {
			int ch = sis.read();
			if (ch == -1) {
				result = false;
				break;
			}
			sb.append((char)ch);
			result = c == (char)ch;
			if (!result) {
				break;
			}
		}
		
		if (result) {
			context.push(createFragment(context, sb.toString()));
		} else if (sb.length() > 0) {
			sis.unread(sb.toString());
		}
		
		return result;
	}
	
	protected Fragment createFragment(Context context, String content) {
		Fragment fragment = new DefaultFragmentImpl(context.getFormatProvider());
		fragment.setId(this.getId());
		fragment.setFragment(content);
		fragment.setFormattedFragment(applyHTMLRules(content));
		return fragment;
	}
}
