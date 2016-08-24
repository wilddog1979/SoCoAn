package org.eaSTars.socoan.lang.java.blocksandstatements.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class LocalVariableDeclarationStatementTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "LocalVariableDeclarationStatement";
	
	//TODO: null, boolean values are not indicated as keyword. This must be a defect in Expression and it should be investigated
	
	@Test
	public void testsimplereference() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("AbstractTypeDeclaration typeDeclaration = null; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"AbstractTypeDeclaration typeDeclaration = null;",
				"AbstractTypeDeclaration typeDeclaration = null;");
		
		checkLeftover(sis, 9);
	}
	
	@Test
	public void testprimitiveint() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("int identifier = 0; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"int identifier = 0;",
				"<span class=\"keyword\">int</span> identifier = 0;");
		
		checkLeftover(sis, 9);
	}
	
	@Test
	public void testprimitiveboolean() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("boolean identifier = true; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"boolean identifier = true;",
				"<span class=\"keyword\">boolean</span> identifier = true;");
		
		checkLeftover(sis, 9);
	}
	
	@Test
	public void testreferencearray() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("String[] identifier = {, }; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"String[] identifier = {, };",
				"String[] identifier = {, };");
		
		checkLeftover(sis, 9);
	}
}
