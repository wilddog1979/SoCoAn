package org.eaSTars.socoan.lang.java.classes.test;

import static org.junit.Assert.assertEquals;
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
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class EnumDeclarationTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "EnumDeclaration";
	
	@Test
	public void testempty() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("public enum Identifier{} leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"public enum Identifier{}",
				"<span class=\"keyword\">public</span> <span class=\"keyword\">enum</span> Identifier{}");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testemptywithsuperinterfaces() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("public enum Identifier1 implements Identifier2, Identifier3{} leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"public enum Identifier1 implements Identifier2, Identifier3{}",
				"<span class=\"keyword\">public</span> <span class=\"keyword\">enum</span> Identifier1 <span class=\"keyword\">implements</span> Identifier2, Identifier3{}");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testsimplenum() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("public enum Identifier1{Identifier2, Identifier3,} leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"public enum Identifier1{Identifier2, Identifier3,}",
				"<span class=\"keyword\">public</span> <span class=\"keyword\">enum</span> Identifier1{Identifier2, Identifier3,}");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testsimplenumwithclassbody() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("public enum Identifier1{Identifier2{}, Identifier3{},} leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"public enum Identifier1{Identifier2{}, Identifier3{},}",
				"<span class=\"keyword\">public</span> <span class=\"keyword\">enum</span> Identifier1{Identifier2{}, Identifier3{},}");
		
		checkLeftover(sis, " leftover");
	}
}
