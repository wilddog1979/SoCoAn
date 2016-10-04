package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class EncodingDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "EncodingDecl";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValueDoubleQuote() {
		testRecognized(
				" encoding=\"utf-8\" leftover",
				" encoding=\"utf-8\"",
				" encoding=\"utf-8\"",
				" leftover");
	}
	
	@Test
	public void testSimpleValueSingleQuote() {
		testRecognized(
				" encoding='utf-8' leftover",
				" encoding='utf-8'",
				" encoding='utf-8'",
				" leftover");
	}
}
