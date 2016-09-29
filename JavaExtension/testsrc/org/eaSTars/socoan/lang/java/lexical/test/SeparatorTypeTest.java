package org.eaSTars.socoan.lang.java.lexical.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import org.eaSTars.socoan.lang.java.CommentFragment;
import org.eaSTars.socoan.lang.java.SeparatorFragment;
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class SeparatorTypeTest extends AbstractJavaLangTest {

	private static final String ELEMENT_NAME = "Separator";
	
	@Test
	public void testRecognizeSpace() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(" leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, " ", " ");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeTab() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("\tleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, "\t", "\t");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeNewline() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, "\n", "\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeNoMatch() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeMultiplePlain() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(" \t\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t\n", " \t\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeMultipleWithLinecomment() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(" \t//linecomment\n\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t//linecomment\n\n", " \t<span class=\"linecomment\">//linecomment\n</span>\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeMultipleWithBlockcomment() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(" \t/*blockcomment*/\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t/*blockcomment*/\n", " \t<span class=\"blockcomment\">/*blockcomment*/</span>\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeMultipleWithJavadoc() {
		AbstractTypeDeclaration comments = null;
		Context context = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("element type should be found", comments);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream(" \t/**javadoc*/\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of SeparatorFragment", fragment instanceof SeparatorFragment);
		SeparatorFragment separatorfragment = (SeparatorFragment) fragment;
		assertEquals("ID of the fragment should match", "Separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t/**javadoc*/\n", " \t<span class=\"javadoc\">/**javadoc*/</span>\n");
		CommentFragment commentFragment = separatorfragment.getJavadoc();
		assertNotNull("SeparatorFragment should contain a javadoc elements", commentFragment);
		testCommentFragment(
				commentFragment,
				CommentFragment.Type.JavaDoc,
				"/**javadoc*/",
				"<span class=\"javadoc\">/**javadoc*/</span>",
				"javadoc"
		);
		
		checkLeftover(sis, "leftover");
	}
}
