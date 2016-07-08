package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.CommentFragment;
import org.eaSTars.socoan.lang.java.SeparatorFragment;
import org.junit.Test;

public class SeparatorTypeTest extends AbstractJavaLangTest {

	@Test
	public void testRecognizeSpace() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" leftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, " ", " ");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeTab() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("\tleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, "\t", "\t");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeNewline() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("\nleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, "\n", "\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeNoMatch() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("leftover".getBytes()));
		
		Context context = new Context();
		
		boolean testresult = false;
		try {
			testresult = comments.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeMultiplePlain() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t\nleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t\n", " \t\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeMultipleWithLinecomment() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t//linecomment\n\nleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t//linecomment\n\n", " \t<span class=\"linecomment\">//linecomment\n</span>\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeMultipleWithBlockcomment() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t/*blockcomment*/\nleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
		testFragment(separatorfragment, " \t/*blockcomment*/\n", " \t<span class=\"blockcomment\">/*blockcomment*/</span>\n");
		assertNull("SeparatorFragment should not contain any javadoc elements", separatorfragment.getJavadoc());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeMultipleWithJavadoc() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("separator");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(" \t/**javadoc*/\nleftover".getBytes()));
		
		Context context = new Context();
		
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
		assertEquals("ID of the fragment should match", "separator", separatorfragment.getId());
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
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
