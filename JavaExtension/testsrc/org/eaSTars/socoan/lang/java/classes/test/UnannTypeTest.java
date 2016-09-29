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

public class UnannTypeTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "UnannType";
	
	@Test
	public void testint() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("int leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "int", "<span class=\"keyword\">int</span>");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testintarray() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("int[] leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "int[]", "<span class=\"keyword\">int</span>[]");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testintmultiarray() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("int[][][] leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "int[][][]", "<span class=\"keyword\">int</span>[][][]");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testIdentifier() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("Identifier leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "Identifier", "Identifier");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testIdentifierArgument() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("Identifier1 <? extends Identifier2> leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment,
				"Identifier1 <? extends Identifier2>",
				"Identifier1 &lt;? <span class=\"keyword\">extends</span> Identifier2&gt;"
		);
		
		checkLeftover(sis, " leftover");
	}
}
