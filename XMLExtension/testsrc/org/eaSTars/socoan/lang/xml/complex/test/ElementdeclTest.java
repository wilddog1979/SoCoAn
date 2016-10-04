package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class ElementdeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "elementdecl";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleEmpty() {
		testRecognized(
				"<!ELEMENT br EMPTY> leftover",
				"<!ELEMENT br EMPTY>",
				"&lt;!ELEMENT br EMPTY&gt;",
				" leftover");
	}
	
	@Test
	public void testSimpleAny() {
		testRecognized(
				"<!ELEMENT container ANY> leftover",
				"<!ELEMENT container ANY>",
				"&lt;!ELEMENT container ANY&gt;",
				" leftover");
	}
	
	//Mixed and children are tested separate
}
