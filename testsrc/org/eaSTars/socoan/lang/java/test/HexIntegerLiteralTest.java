package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.assertEquals;
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
import org.junit.Test;

public class HexIntegerLiteralTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "IntegerLiteral";
	
	@Test
	public void testSimple0_1() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x0 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x0", "0x0");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0_2() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x0g leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x0", "0x0");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 10, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0L() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x0L leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x0L", "0x0L");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple1() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x1 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x1", "0x1");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple12() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x12 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x12", "0x12");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple123() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x123 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x123", "0x123");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple123_45() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x123_45 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x123_45", "0x123_45");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleMax() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0x2147483647 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0x2147483647", "0x2147483647");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
