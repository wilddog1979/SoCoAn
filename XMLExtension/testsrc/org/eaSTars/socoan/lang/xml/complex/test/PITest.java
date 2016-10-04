package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class PITest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "PI";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValue() {
		testRecognized(
				"<?test?> leftover",
				"<?test?>",
				"&lt;?test?&gt;",
				" leftover");
	}
	
	@Test
	public void testSimpleValueWithAdditionalValue() {
		testRecognized(
				"<?test moretest value?> leftover",
				"<?test moretest value?>",
				"&lt;?test moretest value?&gt;",
				" leftover");
	}
}
