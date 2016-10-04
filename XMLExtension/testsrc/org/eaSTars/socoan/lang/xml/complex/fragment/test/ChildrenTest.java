package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class ChildrenTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "children";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleSeq() {
		testRecognized(
				"(front, body, back?) leftover",
				"(front, body, back?)",
				"(front, body, back?)",
				" leftover");
	}
	
	@Test
	public void testSimpleChoice() {
		testRecognized(
				"(div.mix | dict.mix) leftover",
				"(div.mix | dict.mix)",
				"(div.mix | dict.mix)",
				" leftover");
	}
	
	@Test
	public void testMixed() {
		testRecognized(
				"(head, (p | list | note)*, div2*) leftover",
				"(head, (p | list | note)*, div2*)",
				"(head, (p | list | note)*, div2*)",
				" leftover");
	}
}
