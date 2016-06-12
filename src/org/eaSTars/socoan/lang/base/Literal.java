package org.eaSTars.socoan.lang.base;

import java.io.IOException;
import java.util.Stack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.LanguageNode;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Literal", propOrder = {
    "literal"
})
public class Literal extends LanguageNode {

	@XmlAttribute(name="literal")
    protected String literal;

	public Literal() {
	}
	
	public Literal(String literal) {
		this.literal = literal;
	}
	
	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}
	
	@Override
	public boolean recognizeNode(Stack<LanguageFragment> context, SourcecodeInputStream sis) throws IOException {
		boolean result = true;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < literal.length(); ++i) {
			int ch = sis.read();
			if (ch == -1) {
				result = false;
			} else {
				sb.append((char)ch);
				result = literal.charAt(i) == (ch & 0xff);
			}
			if (!result) {
				break;
			}
		}
		
		if (result) {
			context.push(new LiteralFragment(sb.toString()));
		} else if (sb.length() != 0) {
			sis.unread(sb.toString().getBytes());
		}
		
		return result;
	}
}
