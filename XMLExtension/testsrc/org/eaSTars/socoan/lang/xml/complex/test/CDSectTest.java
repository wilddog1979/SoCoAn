package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class CDSectTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "CDSect";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValue() {
		testRecognized(
				"<![CDATA[test]]> leftover",
				"<![CDATA[test]]>",
				"&lt;![CDATA[test]]&gt;",
				" leftover");
	}
}
