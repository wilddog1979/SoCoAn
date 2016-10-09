package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.junit.Test;

public class LiteralTypeTest extends AbstractLangTest {

	@Test
	public void testRecognizeSimple() {
		LiteralTypeHelper literal = new LiteralTypeHelper("test");

		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("testcontent".getBytes()));
		
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
		
		checkLeftover(sis, "content");
	}

	@Test
	public void testNotRecognizePartial() {
		LiteralTypeHelper literal = new LiteralTypeHelper("test");

		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("te".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = literal.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "te");
	}
}
