package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class SeparatorTest {

	@Test
	public void test() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t// some comment\n\nspace tab new line".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Separator",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", " some comment", literalFragment.getContent());
		assertEquals("TerminatedNode should have the matching sample", " \t// some comment\n\n", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 18, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

}
