package org.eaSTars.socoan.lang.base.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.base.Literal;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class LiteralTest {

	@Test
	public void testLiteralRecognizeCompleteBuffer() {
		LanguageContext context = new LanguageContext(null);
		
		Literal literal = new Literal();
		literal.setLiteral("test");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("test".getBytes()));
		
		boolean result = false;
		try {
			result = literal.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("LiteralFragment should contain the matched sample", "test", literalFragment.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment.getContent() == null);
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testLiteralRecognizeWithLeftover() {
		LanguageContext context = new LanguageContext(null);
		
		Literal literal = new Literal();
		literal.setLiteral("test");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("testbuffer".getBytes()));
		
		boolean result = false;
		try {
			result = literal.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", result);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		LanguageFragment fragment = context.pop();
		assertTrue("Context buffer should contain an instance of LiteralFragment", fragment instanceof LiteralFragment);
		LiteralFragment literalFragment = (LiteralFragment) fragment;
		assertEquals("LiteralFragment should contain the matched sample", "test", literalFragment.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment.getContent() == null);
		
		try {
			assertEquals("InputStream should comtain leftover data", 6, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testLiteralNotRecognized() {
		LanguageContext context = new LanguageContext(null);
		
		Literal literal = new Literal();
		literal.setLiteral("test");
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("tesst".getBytes()));
		
		boolean result = false;
		try {
			result = literal.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", result);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("InputStream should contain rolled back data", 5, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
