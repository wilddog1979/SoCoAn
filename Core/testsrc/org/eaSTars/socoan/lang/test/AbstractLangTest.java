package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Optional;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;


public abstract class AbstractLangTest {

	protected void testOptionalString(String name, String expected, Optional<String> actual) {
		if (expected != null) {
			assertTrue(String.format("%s content should be presented", name), actual.isPresent());
			actual.ifPresent(s -> assertEquals(String.format("%s should be equal to the sample", name), expected, s));
		} else {
			assertFalse(String.format("%s content should not be presented", name), actual.isPresent());
		}
	}
	
	protected void testFragment(Fragment fragment, String fragmentcontent, String formattedcontent) {
		testOptionalString("Fragment", fragmentcontent, fragment.getFragment());
		testOptionalString("Formatted fragment", formattedcontent, fragment.getFormattedFragment());
	}

	protected boolean recognizetype(AbstractTypeDeclaration typeDeclaration, Context context, SourcecodeInputStream sis) {
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		return testresult;
	}

	protected void checkLeftover(SourcecodeInputStream sis, int leftover) {
		try {
			if (leftover == 0) {
				assertEquals("The input stream should not contain any leftover characters", leftover, sis.available());
			} else {
				assertEquals("The input stream should contain the leftover characters", leftover, sis.available());
			}
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
