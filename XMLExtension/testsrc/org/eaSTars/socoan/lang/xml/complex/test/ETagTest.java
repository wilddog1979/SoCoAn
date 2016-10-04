package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class ETagTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "ETag";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testSimple() {
		testRecognized(
				"</termdef> leftover",
				"</termdef>",
				"&lt;/termdef&gt;",
				" leftover");
	}
}
