package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.eaSTars.socoan.SourcecodeInputStream;

@XmlAccessorType(XmlAccessType.FIELD)
public class LiteralType extends AbstractTypeDeclaration {

	@XmlAttribute(name="literal")
	protected String literal;
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		
		StringBuffer sb = new StringBuffer();
		for (char c : literal.toCharArray()) {
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
			Fragment fragment = new Fragment();
			fragment.setFragment(sb.toString());
			context.push(fragment);
		} else if (sb.length() > 0) {
			sis.unread(sb.toString().getBytes());
		}
		
		return result;
	}
}
