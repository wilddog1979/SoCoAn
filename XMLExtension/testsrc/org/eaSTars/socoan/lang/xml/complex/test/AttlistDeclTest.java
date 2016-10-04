package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class AttlistDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "AttlistDecl";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testCase1() {
		testRecognized(
				"<!ATTLIST termdef\nid      ID      #REQUIRED\nname    CDATA   #IMPLIED> leftover",
				"<!ATTLIST termdef\nid      ID      #REQUIRED\nname    CDATA   #IMPLIED>",
				"&lt;!ATTLIST termdef\nid      ID      #REQUIRED\nname    CDATA   #IMPLIED&gt;",
				" leftover");
	}
	
	@Test
	public void testCase2() {
		testRecognized(
				"<!ATTLIST list\n          type    (bullets|ordered|glossary)  \"ordered\"> leftover",
				"<!ATTLIST list\n          type    (bullets|ordered|glossary)  \"ordered\">",
				"&lt;!ATTLIST list\n          type    (bullets|ordered|glossary)  \"ordered\"&gt;",
				" leftover");
	}
	
	@Test
	public void testCase3() {
		testRecognized(
				"<!ATTLIST form\n          method  CDATA   #FIXED \"POST\"> leftover",
				"<!ATTLIST form\n          method  CDATA   #FIXED \"POST\">",
				"&lt;!ATTLIST form\n          method  CDATA   #FIXED \"POST\"&gt;",
				" leftover");
	}
}
