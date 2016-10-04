package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class VersionInfoTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "VersionInfo";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValueDoubleQuote() {
		testRecognized(
				" version=\"1.0\" leftover",
				" version=\"1.0\"",
				" version=\"1.0\"",
				" leftover");
	}
	
	@Test
	public void testSimpleValueSingleQuote() {
		testRecognized(
				" version='1.0' leftover",
				" version='1.0'",
				" version='1.0'",
				" leftover");
	}
}
