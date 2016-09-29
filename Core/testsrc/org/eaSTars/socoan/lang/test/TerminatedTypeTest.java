package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.TerminatedType;
import org.junit.Test;

public class TerminatedTypeTest extends AbstractLangTest {

	@Test
	public void testRecognizeSimple1() {
		TerminatedType terminatedtype = new TerminatedType();
		List<String> terminators = terminatedtype.getTerminators();
		terminators.add("term1");
		terminators.add("term2");
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("contentterm1other".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = terminatedtype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "contentterm1", "content");
		
		checkLeftover(sis, "other");
	}

	@Test
	public void testRecognizeSimple2() {
		TerminatedType terminatedtype = new TerminatedType();
		List<String> terminators = terminatedtype.getTerminators();
		terminators.add("term1");
		terminators.add("term2");
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("contentterm2other".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = terminatedtype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "contentterm2", "content");
		
		checkLeftover(sis, "other");
	}
	
	@Test
	public void testNotRecognizeSimple1() {
		TerminatedType terminatedtype = new TerminatedType();
		List<String> terminators = terminatedtype.getTerminators();
		terminators.add("term1");
		terminators.add("term2");
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("contentterm3other".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = terminatedtype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "contentterm3other");
	}
}
