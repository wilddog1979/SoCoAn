package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class EntityDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "EntityDecl";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testGEEntityValue() {
		testRecognized(
				"<!ENTITY testname \"testvalue\"> leftover",
				"<!ENTITY testname \"testvalue\">",
				"&lt;!ENTITY testname \"testvalue\"&gt;",
				" leftover");
	}
	
	@Test
	public void testGEExternalID() {
		testRecognized(
				"<!ENTITY testname SYSTEM \"sysliteral\" NDATA ndata> leftover",
				"<!ENTITY testname SYSTEM \"sysliteral\" NDATA ndata>",
				"&lt;!ENTITY testname SYSTEM \"sysliteral\" NDATA ndata&gt;",
				" leftover");
	}
	
	@Test
	public void testPEEntityValue() {
		testRecognized(
				"<!ENTITY % testname \"testvalue\"> leftover",
				"<!ENTITY % testname \"testvalue\">",
				"&lt;!ENTITY % testname \"testvalue\"&gt;",
				" leftover");
	}
	
	@Test
	public void testPEExternalID() {
		testRecognized(
				"<!ENTITY % testname SYSTEM \"sysliteral\"> leftover",
				"<!ENTITY % testname SYSTEM \"sysliteral\">",
				"&lt;!ENTITY % testname SYSTEM \"sysliteral\"&gt;",
				" leftover");
	}
}
