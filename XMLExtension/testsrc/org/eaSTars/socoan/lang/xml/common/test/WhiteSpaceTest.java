package org.eaSTars.socoan.lang.xml.common.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class WhiteSpaceTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "S";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleSpace() {
		testRecognized(" leftover", " ", " ", "leftover");
	}
	
	@Test
	public void testSimpleTab() {
		testRecognized("\tleftover", "\t", "\t", "leftover");
	}
	
	@Test
	public void testSimpleCR() {
		testRecognized("\rleftover", "\r", "\r", "leftover");
	}
	
	@Test
	public void testSimpleLF() {
		testRecognized("\nleftover", "\n", "\n", "leftover");
	}
	
	@Test
	public void testMultipleCharacters() {
		testRecognized(
				"  \t\t\r\r\n\nleftover",
				"  \t\t\r\r\n\n",
				"  \t\t\r\r\n\n",
				"leftover");
	}
	
	@Test
	public void testNotRecognizing() {
		testNotRecognized("leftover", "leftover");
	}
}
