package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class PrologTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "prolog";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testXMLDeclWithDoctypedecl() {
		testRecognized(
				"<?xml version=\"1.0\"?>\n<!DOCTYPE greeting SYSTEM \"hello.dtd\"> leftover",
				"<?xml version=\"1.0\"?>\n<!DOCTYPE greeting SYSTEM \"hello.dtd\"> ",
				"&lt;?xml version=\"1.0\"?&gt;\n&lt;!DOCTYPE greeting SYSTEM \"hello.dtd\"&gt; ",
				"leftover");
	}
	
	@Test
	public void testXMLDeclWithDoctypedeclAndElement() {
		testRecognized(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE greeting [\n  <!ELEMENT greeting (#PCDATA)>\n]> leftover",
				"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE greeting [\n  <!ELEMENT greeting (#PCDATA)>\n]> ",
				"&lt;?xml version=\"1.0\" encoding=\"UTF-8\" ?&gt;\n&lt;!DOCTYPE greeting [\n  &lt;!ELEMENT greeting (#PCDATA)&gt;\n]&gt; ",
				"leftover");
	}
}
