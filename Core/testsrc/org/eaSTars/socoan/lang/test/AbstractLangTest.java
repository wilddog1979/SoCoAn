package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.util.Optional;

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
}
