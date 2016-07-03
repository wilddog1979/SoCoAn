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
import org.junit.Test;

public class CommentTypeTest {

	@Test
	public void testRecognizeLineComment() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("comments");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("//some comment\nleftover".getBytes()));
		
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
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "comments", commentfragment.getId());
		assertEquals("Fragment should be equal to the recognized piece", "//some comment\n", commentfragment.getFragment());
		assertEquals("Fragment should contain formatted content", "<span class=\"linecomment\">//some comment\n</span>" , commentfragment.getFormattedFragment());
		assertEquals("CommentFragment should contain comment", "some comment", commentfragment.getComment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}

	@Test
	public void testRecognizeBlockComment() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("comments");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("/*\n\tsome\n\tcomment\n*/leftover".getBytes()));
		
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
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "comments", commentfragment.getId());
		assertEquals("Fragment should be equal to the recognized piece", "/*\n\tsome\n\tcomment\n*/", commentfragment.getFragment());
		assertEquals("Fragment should contain formatted content", "<span class=\"blockcomment\">/*\n\tsome\n\tcomment\n*/</span>" , commentfragment.getFormattedFragment());
		assertEquals("CommentFragment should contain comment", "\n\tsome\n\tcomment\n", commentfragment.getComment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeJavadocComment() {
		AbstractTypeDeclaration comments = null;
		try {
			comments = JavaTests.getJavaLang().getTypeDeclaration("comments");
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", comments);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("/**\n\tsome\n\tjavadoc\n*/leftover".getBytes()));
		
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
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "comments", commentfragment.getId());
		assertEquals("Fragment should be equal to the recognized piece", "/**\n\tsome\n\tjavadoc\n*/", commentfragment.getFragment());
		assertEquals("Fragment should contain formatted content", "<span class=\"javadoc\">/**\n\tsome\n\tjavadoc\n*/</span>" , commentfragment.getFormattedFragment());
		assertEquals("CommentFragment should contain comment", "\n\tsome\n\tjavadoc\n", commentfragment.getComment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}