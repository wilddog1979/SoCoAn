package org.eaSTars.socoan.lang.java.types.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.ComplexTypeNodeGroup;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.eaSTars.socoan.lang.test.ComplexTypeHelper;
import org.eaSTars.socoan.lang.test.ComplexTypeNodeHelper;
import org.junit.Test;

public class AnnotationIdentifierTypeAgrumentTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "ClassType";
	
	@Test
	public void testidentifier() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		ComplexTypeHelper complextype = null;
		try {			
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
			
			complextype = new ComplexTypeHelper(ctx -> new LangProcessors().processAggregation(ctx));
			ComplexTypeNodeGroup g = new ComplexTypeNodeGroup();
			ComplexTypeNodeHelper node = new ComplexTypeNodeHelper(typeDeclaration);
			g.getInnerNodes().add(node);
			
			complextype.getStartnodes().add(g);
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("Identifier leftover".getBytes()));
		
		boolean testresult = recognizetype(complextype, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "Identifier", "Identifier");
		
		checkLeftover(sis, " leftover");
	}
	
	@Test
	public void testannotationidentifier() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		ComplexTypeHelper complextype = null;
		try {			
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
			
			complextype = new ComplexTypeHelper(ctx -> new LangProcessors().processAggregation(ctx));
			ComplexTypeNodeGroup g = new ComplexTypeNodeGroup();
			ComplexTypeNodeHelper node = new ComplexTypeNodeHelper(typeDeclaration);
			g.getInnerNodes().add(node);
			
			complextype.getStartnodes().add(g);
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("@testannotation Identifier leftover".getBytes()));
		
		boolean testresult = recognizetype(complextype, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "@testannotation Identifier", "@testannotation Identifier");
		
		checkLeftover(sis, " leftover");
	}
}
