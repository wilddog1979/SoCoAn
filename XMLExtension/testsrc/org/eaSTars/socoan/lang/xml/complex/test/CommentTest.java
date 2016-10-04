package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class CommentTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "Comment";

	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}
	
	@Test
	public void testSimpleComment() {
		testRecognized(
				"<!-- test comment --> leftover",
				"<!-- test comment -->",
				"&lt;!-- test comment --&gt;",
				" leftover");
	}
	
	@Test
	public void testNotWellFormed() {
		testNotRecognized(
				"<!-- B+, B, or B---> leftover",
				"<!-- B+, B, or B---> leftover");
	}
}
