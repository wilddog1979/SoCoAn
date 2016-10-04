package org.eaSTars.socoan.lang.xml.common.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class CharTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "Char";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleMatch9() {
		testRecognized("\tleftover", "\t", "\t", "leftover");
	}

	@Test
	public void testSimpleMatch_r() {
		testRecognized("\rleftover", "\r", "\r", "leftover");
	}
	
	@Test
	public void testSimpleMatch_n() {
		testRecognized("\nleftover", "\n", "\n", "leftover");
	}
	
	@Test
	public void testNoMatch_7() {
		testNotRecognized("\u0007leftover", "\u0007leftover");
	}
}
