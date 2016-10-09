package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Optional;

import org.eaSTars.socoan.SourcecodeInputReader;
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

	protected boolean recognizetype(AbstractTypeDeclaration typeDeclaration, Context context, SourcecodeInputReader sis) {
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		return testresult;
	}

	protected void checkLeftover(SourcecodeInputReader sis, String content) {
		try {
			StringBuffer buffer = new StringBuffer();
			int c = -1;
			while((c = sis.read()) != -1) {
				buffer.append((char)c);
			}
			if (content.length() == 0) {
				assertEquals("The input stream should not contain any leftover characters", content, buffer.toString());
			} else {
				assertEquals("The input stream should contain the leftover characters", content, buffer.toString());
			}
			sis.unread(buffer.toString());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
