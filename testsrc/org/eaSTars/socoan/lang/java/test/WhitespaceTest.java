package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class WhitespaceTest {

	@Test
	public void testSpace() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" space".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Whitespaces",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertTrue("TerminatedNode should have the matching content", literalFragment.getContent() == null);
		assertEquals("TerminatedNode should have the matching sample", " ", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 5, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testTab() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("\ttab".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Whitespaces",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertTrue("TerminatedNode should have the matching content", literalFragment.getContent() == null);
		assertEquals("TerminatedNode should have the matching sample", "\t", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 3, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testNewLine() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("\nnew line".getBytes()));
		
		boolean result = false;
		try {
			result = JavaTests.java.recognize("Whitespaces",context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertTrue("TerminatedNode should have the matching content", literalFragment.getContent() == null);
		assertEquals("TerminatedNode should have the matching sample", "\n", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 8, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testMultiple() {
		LanguageContext context = new LanguageContext(null);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t\nspace tab new line".getBytes()));
		
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
		assertTrue("TerminatedNode should have the matching content", literalFragment.getContent() == null);
		assertEquals("TerminatedNode should have the matching sample", " \t\n", literalFragment.getFragment());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 18, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
