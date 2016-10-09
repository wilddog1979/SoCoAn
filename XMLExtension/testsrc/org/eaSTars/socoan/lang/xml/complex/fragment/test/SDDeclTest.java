package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class SDDeclTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "SDDecl";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValueDoubleQuote() {
		testRecognized(
				" standalone=\"yes\" leftover",
				new String[][]{
					{" ", " "},
					{"standalone", "standalone"},
					{"=", "="},
					{"\"", "\""},
					{"yes", "yes"},
					{"\"", "\""}
				},
				" leftover");
	}
	
	@Test
	public void testSimpleValueSingleQuote() {
		testRecognized(
				" standalone='no' leftover",
				new String[][]{
					{" ", " "},
					{"standalone", "standalone"},
					{"=", "="},
					{"'", "'"},
					{"no", "no"},
					{"'", "'"}
				},
				" leftover");
	}
}
