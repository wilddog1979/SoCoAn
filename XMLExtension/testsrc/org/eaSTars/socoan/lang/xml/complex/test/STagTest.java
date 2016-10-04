package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class STagTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "STag";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testSimple() {
		testRecognized(
				"<termdef id=\"dt-dog\" term=\"dog\"> leftover",
				"<termdef id=\"dt-dog\" term=\"dog\">",
				"&lt;termdef id=\"dt-dog\" term=\"dog\"&gt;",
				" leftover");
	}
}
