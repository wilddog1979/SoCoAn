package org.eaSTars.socoan.lang.base.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.eaSTars.socoan.lang.base.TerminatedNode;
import org.junit.Test;

public class TerminatedNodeTest {

	@Test
	public void testSimpleMatch1() {
		LanguageContext context = new LanguageContext(null);
		
		TerminatedNode terminatedNode = new TerminatedNode();
		List<String> terminators = terminatedNode.getTerminators();
		terminators.add("bc");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean result = false;
		try {
			result = terminatedNode.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", "abc", literalFragment.getFragment());
		assertEquals("TerminatedNode should have the matching sample", "a", literalFragment.getContent());
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testSimpleMatch2() {
		LanguageContext context = new LanguageContext(null);
		
		TerminatedNode terminatedNode = new TerminatedNode();
		List<String> terminators = terminatedNode.getTerminators();
		terminators.add("\\n");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("a\n".getBytes()));
		
		boolean result = false;
		try {
			result = terminatedNode.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", "a\n", literalFragment.getFragment());
		assertEquals("TerminatedNode should have the matching sample", "a", literalFragment.getContent());
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testSimpleMatch3() {
		LanguageContext context = new LanguageContext(null);
		
		TerminatedNode terminatedNode = new TerminatedNode();
		List<String> terminators = terminatedNode.getTerminators();
		terminators.add("b");
		terminators.add("c");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean result = false;
		try {
			result = terminatedNode.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", "ab", literalFragment.getFragment());
		assertEquals("TerminatedNode should have the matching sample", "a", literalFragment.getContent());
		
		try {
			assertEquals("InputStream should not be empty", 1, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testSimpleMatch4() {
		LanguageContext context = new LanguageContext(null);
		
		TerminatedNode terminatedNode = new TerminatedNode();
		List<String> terminators = terminatedNode.getTerminators();
		terminators.add("b");
		terminators.add("c");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("acb".getBytes()));
		
		boolean result = false;
		try {
			result = terminatedNode.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("TerminatedNode should have the matching content", "ac", literalFragment.getFragment());
		assertEquals("TerminatedNode should have the matching sample", "a", literalFragment.getContent());
		
		try {
			assertEquals("InputStream should not be empty", 1, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testNoMatch1() {
		LanguageContext context = new LanguageContext(null);
		
		TerminatedNode terminatedNode = new TerminatedNode();
		List<String> terminators = terminatedNode.getTerminators();
		terminators.add("b");
		terminators.add("c");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("adef".getBytes()));
		
		boolean result = false;
		try {
			result = terminatedNode.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", result);
		assertEquals("Context buffer should be empty", 0, context.size());
		
		try {
			assertEquals("InputStream should contain the original values", 4, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
