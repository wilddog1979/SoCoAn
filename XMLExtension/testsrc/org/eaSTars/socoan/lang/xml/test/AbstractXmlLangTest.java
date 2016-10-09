package org.eaSTars.socoan.lang.xml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.test.AbstractLangTest;

public abstract class AbstractXmlLangTest extends AbstractLangTest {
	
	public abstract String getElementName();
	
	protected void testRecognized(String rawinput, String fragmentcontent, String formattedcontent, String leftover) {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = XMLTests.getXmlLang().getTypeDeclaration(getElementName());
			context = new Context(XMLTests.getXmlLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(rawinput.getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		
		Fragment fragment = context.pop();
		testFragment(fragment, fragmentcontent, formattedcontent);
		
		checkLeftover(sis, leftover);
	}
	
	protected void testRecognized(String rawinput, String[][] content, String leftover) {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = XMLTests.getXmlLang().getTypeDeclaration(getElementName());
			context = new Context(XMLTests.getXmlLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(rawinput.getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain entries", content.length, context.size());
		
		int index = 0;
		for (String[] entry : content) {
			assertEquals("Sample array should contain raw content and formatted content to check", 2, entry.length);
			
			Fragment fragment = context.get(index++);
			testFragment(fragment, entry[0], entry[1]);
		}
		
		checkLeftover(sis, leftover);
	}
	
	protected void testNotRecognized(String rawinput, String leftover) {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = XMLTests.getXmlLang().getTypeDeclaration(getElementName());
			context = new Context(XMLTests.getXmlLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(rawinput.getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, leftover);
	}
}
