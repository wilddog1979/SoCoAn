package org.eaSTars.socoan.lang.java.lexical.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class IdentifierTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "Identifier";
	
	@Test
	public void testRecognizeSimpleidentifier1() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("_test123 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "_test123", "_test123");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testNoRecognizeKeyword() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("class leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 14, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeKeywordlike() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("classes leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "classes", "classes");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testNoRecognizeBooleanTrue() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("true leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 13, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testNoRecognizeBooleanFalse() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("false leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 14, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testNoRecognizeNull() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("null leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 13, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeNulllike() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("nullable leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "nullable", "nullable");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
