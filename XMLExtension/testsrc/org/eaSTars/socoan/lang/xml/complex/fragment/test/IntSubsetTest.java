package org.eaSTars.socoan.lang.xml.complex.fragment.test;

import static org.junit.Assert.*;

import org.eaSTars.socoan.lang.DefaultFragmentImpl;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.xml.component.PEReference;
import org.eaSTars.socoan.lang.xml.test.AbstractXmlLangTest;
import org.junit.Test;

public class IntSubsetTest extends AbstractXmlLangTest {

	private static final String ELEMENT_NAME = "intSubset";
	
	@Override
	public String getElementName() {
		return ELEMENT_NAME;
	}

	@Test
	public void testPEReference() {
		Fragment fragment = testRecognized("%testname;leftover", "%testname;", "%testname;", "leftover");
		
		assertTrue("Fragment should be an instance of PEReference", fragment instanceof PEReference);
		PEReference peReference = (PEReference) fragment;
		assertEquals("ID should match", "PEReference", peReference.getId());
		assertEquals("Name should match", "testname", peReference.getName());
	}
	
	@Test
	public void testS() {
		Fragment fragment = testRecognized(" leftover", " ", " ", "leftover");
		
		assertTrue("Fragment should be an instance of DefaultFragmentImpl", fragment instanceof DefaultFragmentImpl);
		assertEquals("ID should match", "S", fragment.getId());
	}
}
