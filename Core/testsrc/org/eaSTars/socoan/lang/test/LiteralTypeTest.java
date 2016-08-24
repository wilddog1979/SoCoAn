package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.junit.Test;

public class LiteralTypeTest extends AbstractLangTest {

	@Test
	public void testRecognizeSimple() {
		LiteralTypeHelper literal = new LiteralTypeHelper("test");

		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("testcontent".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = literal.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testOptionalString("Fragment", "test", fragment.getFragment());
		testOptionalString("Formatted fragment", "test", fragment.getFormattedFragment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 7, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}

	@Test
	public void testNotRecognizePartial() {
		LiteralTypeHelper literal = new LiteralTypeHelper("test");

		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("te".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = literal.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 2, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
