package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class CommentNodeTest {

	@Test
	public void testLineComment() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("//some test comment\nthis is not a comment".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Comment",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", "some test comment", literalFragment.getContent());
		assertEquals("TerminatedNode should have the matching sample", "//some test comment\n", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain some data", 21, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testBlockComment() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("/* some\n * test\n * comment\n*/this is not a comment".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Comment",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", " some\n * test\n * comment\n", literalFragment. getContent());
		assertEquals("TerminatedNode should have the matching sample", "/* some\n * test\n * comment\n*/", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain some data", 21, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
