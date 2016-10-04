package org.eaSTars.socoan.lang.xml.common.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class CharDataTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "CharData";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValue() {
		testRecognized(
				"test< leftover",
				"test",
				"test",
				"< leftover");
	}
	
	@Test
	public void testSimpleValueWithCDATA() {
		testNotRecognized(
				"test]]>test< leftover",
				"test]]>test< leftover");
	}
}
