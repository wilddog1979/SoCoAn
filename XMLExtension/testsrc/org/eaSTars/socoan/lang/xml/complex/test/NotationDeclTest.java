package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class NotationDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "NotationDecl";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimple1() {
		testRecognized(
				"<!NOTATION testname SYSTEM \"something\"> leftover",
				"<!NOTATION testname SYSTEM \"something\">",
				"&lt;!NOTATION testname SYSTEM \"something\"&gt;",
				" leftover");
	}
	
	@Test
	public void testSimple2() {
		testRecognized(
				"<!NOTATION testname PUBLIC \"something1\" \"something2\"> leftover",
				"<!NOTATION testname PUBLIC \"something1\" \"something2\">",
				"&lt;!NOTATION testname PUBLIC \"something1\" \"something2\"&gt;",
				" leftover");
	}
	
	@Test
	public void testSimple3() {
		testRecognized(
				"<!NOTATION testname PUBLIC \"something\"> leftover",
				"<!NOTATION testname PUBLIC \"something\">",
				"&lt;!NOTATION testname PUBLIC \"something\"&gt;",
				" leftover");
	}
}
