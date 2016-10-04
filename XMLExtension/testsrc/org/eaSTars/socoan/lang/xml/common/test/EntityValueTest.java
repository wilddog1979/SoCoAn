package org.eaSTars.socoan.lang.xml.common.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class EntityValueTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "EntityValue";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleValueDoubleQuote() {
		testRecognized(
				"\"test\" leftover",
				"\"test\"",
				"\"test\"",
				" leftover");
	}
	
	@Test
	public void testSimpleValueSingleQuote() {
		testRecognized(
				"'test' leftover",
				"'test'",
				"'test'",
				" leftover");
	}
	
	@Test
	public void testPERefDoubleQuote() {
		testRecognized(
				"\"init%test;value\" leftover",
				"\"init%test;value\"",
				"\"init%test;value\"",
				" leftover");
	}
	
	@Test
	public void testEntityRefDoubleQuote() {
		testRecognized(
				"\"init&test;value\" leftover",
				"\"init&test;value\"",
				"\"init&amp;test;value\"",
				" leftover");
	}
	
	@Test
	public void testCharRefDoubleQuote() {
		testRecognized(
				"\"init&#0065;value\" leftover",
				"\"init&#0065;value\"",
				"\"init&amp;#0065;value\"",
				" leftover");
	}
	
	@Test
	public void testHexCharRefDoubleQuote() {
		testRecognized(
				"\"init&#0041;value\" leftover",
				"\"init&#0041;value\"",
				"\"init&amp;#0041;value\"",
				" leftover");
	}
}
