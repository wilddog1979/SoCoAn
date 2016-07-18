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

public class BinaryIntegerLiteralTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "IntegerLiteral";
	
	@Test
	public void testSimple0b0_1() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b0 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b0", "0b0");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0b0_2() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b02 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b0", "0b0");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 10, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0B0L() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0B0L leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0B0L", "0B0L");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0b1() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b1 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b1", "0b1");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0b1011() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b1011 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b1011", "0b1011");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0b1011_111() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b1011_111 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b1011_111", "0b1011_111");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimple0b1011_111_1111_1000() {
		AbstractTypeDeclaration identifiersChars = null;
		Context context = null;
		try {
			identifiersChars = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", identifiersChars);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("0b1011_111_1111_1000 leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = identifiersChars.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "0b1011_111_1111_1000", "0b1011_111_1111_1000");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 9, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
