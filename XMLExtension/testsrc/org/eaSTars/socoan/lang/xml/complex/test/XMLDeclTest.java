package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class XMLDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "XMLDecl";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimple() {
		testRecognized(
				"<?xml version=\"1.0\"?> leftover",
				"<?xml version=\"1.0\"?>",
				"&lt;?xml version=\"1.0\"?&gt;",
				" leftover");
	}
	
	@Test
	public void testSimpleWithSDDecl() {
		testRecognized(
				"<?xml version=\"1.0\" standalone='yes'?> leftover",
				"<?xml version=\"1.0\" standalone='yes'?>",
				"&lt;?xml version=\"1.0\" standalone='yes'?&gt;",
				" leftover");
	}
	
	@Test
	public void testCompelete() {
		testRecognized(
				"<?xml version=\"1.0\" encoding=\"utf-8\" standalone='yes'   ?> leftover",
				"<?xml version=\"1.0\" encoding=\"utf-8\" standalone='yes'   ?>",
				"&lt;?xml version=\"1.0\" encoding=\"utf-8\" standalone='yes'   ?&gt;",
				" leftover");
	}
}
