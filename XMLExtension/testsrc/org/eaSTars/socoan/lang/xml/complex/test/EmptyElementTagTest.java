package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class EmptyElementTagTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "EmptyElementTag";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testSimple() {
		testRecognized(
				"<IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" /> leftover",
				"<IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" />",
				"&lt;IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" /&gt;",
				" leftover");
	}
}
