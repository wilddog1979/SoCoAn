package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class ElementTagTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "element";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testEmpty() {
		testRecognized(
				"<IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" /> leftover",
				"<IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" />",
				"&lt;IMG align=\"left\"\n src=\"http://www.w3.org/Icons/WWW/w3c_home\" /&gt;",
				" leftover");
	}
	
	@Test
	public void testNonEmpty() {
		testRecognized(
				"<termdef id=\"dt-dog\" term=\"dog\"> somecontent </termdef> leftover",
				"<termdef id=\"dt-dog\" term=\"dog\"> somecontent </termdef>",
				"&lt;termdef id=\"dt-dog\" term=\"dog\"&gt; somecontent &lt;/termdef&gt;",
				" leftover");
	}
}
