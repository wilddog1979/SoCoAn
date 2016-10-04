package org.eaSTars.socoan.lang.xml.common.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class PubidLiteralTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "PubidLiteral";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValueDoubleQuote() {
		testRecognized(
				"\"test\" leftover",
				"\"test\"",
				"\"test\"",
				" leftover");
	}
	
	@Test
	public void testSimpleValueSingleQuote() {
		testRecognized(
				"'test' leftover",
				"'test'",
				"'test'",
				" leftover");
	}
}
