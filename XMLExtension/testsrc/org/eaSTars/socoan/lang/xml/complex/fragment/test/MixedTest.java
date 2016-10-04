package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class MixedTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "Mixed";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimple1() {
		testRecognized(
				"(#PCDATA|a|ul|b|i|em)* leftover",
				"(#PCDATA|a|ul|b|i|em)*",
				"(#PCDATA|a|ul|b|i|em)*",
				" leftover");
	}
	
	@Test
	public void testSimple2() {
		testRecognized(
				"(#PCDATA) leftover",
				"(#PCDATA)",
				"(#PCDATA)",
				" leftover");
	}
	
	@Test
	public void testComplex() {
		testRecognized(
				"(#PCDATA | font | phrase | special | form)* leftover",
				"(#PCDATA | font | phrase | special | form)*",
				"(#PCDATA | font | phrase | special | form)*",
				" leftover");
	}
}
