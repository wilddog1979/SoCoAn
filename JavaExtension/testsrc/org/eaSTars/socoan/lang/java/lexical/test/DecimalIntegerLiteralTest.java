package org.eaSTars.socoan.lang.java.lexical.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class DecimalIntegerLiteralTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "IntegerLiteral";
	
	@Test
	public void testSimple0_1() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("0 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0", "0");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimple0_2() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("0a leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0", "0");
		
		checkLeftover(sis, "a leftover");
	}
	
	@Test
	public void testSimple0L() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("0L leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0L", "0L");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimple1() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("1 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "1", "1");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimple12() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("12 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "12", "12");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimple123() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("123 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "123", "123");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimple123_45() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("123_45 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "123_45", "123_45");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testSimpleMax() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("2147483647 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "2147483647", "2147483647");
		
		checkLeftover(sis, " leftover");
	}
}
