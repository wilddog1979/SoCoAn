package org.eaSTars.socoan.lang.java.packages.test;

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

public class PackageDeclarationTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "PackageDeclaration";
	
	@Test
	public void testthispackagename() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("package org.eaSTars.socoan.lang.java.packages.test; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "package org.eaSTars.socoan.lang.java.packages.test;", "<span class=\"keyword\">package</span> org.eaSTars.socoan.lang.java.packages.test;");
		
		checkLeftover(sis, 9);
	}
	
	@Test
	public void testshortpackagename() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", typeDeclaration);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("package org; leftover".getBytes()));
		
		boolean testresult = recognizetype(typeDeclaration, context, sis);
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "package org;", "<span class=\"keyword\">package</span> org;");
		
		checkLeftover(sis, 9);
	}
}