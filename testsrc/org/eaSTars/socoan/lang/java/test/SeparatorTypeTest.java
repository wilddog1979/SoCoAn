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

public class SeparatorTypeTest {

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
		assertEquals("Fragment should be equal to the recognized piece", " ", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", " " , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", "\t", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", "\t" , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", "\n", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", "\n" , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", " \t\n", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", " \t\n" , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", " \t//linecomment\n\n", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", " \t<span class=\"linecomment\">//linecomment\n</span>\n" , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", " \t/*blockcomment*/\n", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", " \t<span class=\"blockcomment\">/*blockcomment*/</span>\n" , separatorfragment.getFormattedFragment());
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
		assertEquals("Fragment should be equal to the recognized piece", " \t/**javadoc*/\n", separatorfragment.getFragment());
		assertEquals("Fragment should contain formatted content", " \t<span class=\"javadoc\">/**javadoc*/</span>\n" , separatorfragment.getFormattedFragment());
		assertNotNull("SeparatorFragment should contain a javadoc elements", separatorfragment.getJavadoc());
		CommentFragment commentFragment = separatorfragment.getJavadoc();
		assertEquals("Comment element should contain java doc", CommentFragment.Type.JavaDoc, commentFragment.getType());
		assertEquals("Javadoc comment element fragment should contain the recognized piece", "/**javadoc*/", commentFragment.getFragment());
		assertEquals("Javadoc comment element formatted fragment should contain the recognized piece", "<span class=\"javadoc\">/**javadoc*/</span>", commentFragment.getFormattedFragment());
		assertEquals("Javadoc comment element should contain the recognized piece", "javadoc", commentFragment.getComment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
