package org.eaSTars.socoan.lang.java.lexical.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.eaSTars.socoan.lang.java.test.AbstractJavaLangTest;
import org.eaSTars.socoan.lang.java.test.JavaTests;
import org.junit.Test;

public class CommentTypeTest extends AbstractJavaLangTest{

	private static final String ELEMENT_NAME = "Comment";
	
	@Test
	public void testRecognizeEndOfLineComment() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("//some comment\nleftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "Comment", commentfragment.getId());
		testCommentFragment(
				commentfragment,
				CommentFragment.Type.EndOfLineComment,
				"//some comment\n",
				"<span class=\"linecomment\">//some comment\n</span>",
				"some comment"
		);
		
		checkLeftover(sis, "leftover");
	}

	@Test
	public void testRecognizeTraditionalComment() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("/*\n\tsome\n\tcomment\n*/leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "Comment", commentfragment.getId());
		testCommentFragment(
				commentfragment,
				CommentFragment.Type.TraditionalComment,
				"/*\n\tsome\n\tcomment\n*/",
				"<span class=\"blockcomment\">/*\n\tsome\n\tcomment\n*/</span>",
				"\n\tsome\n\tcomment\n"
		);
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testRecognizeJavadocComment() {
		AbstractTypeDeclaration typeDeclaration = null;
		Context context = null;
		try {
			typeDeclaration = JavaTests.getJavaLang().getTypeDeclaration(ELEMENT_NAME);
			context = new Context(JavaTests.getJavaLang());
		} catch (JAXBException | ReferenceNotFoundException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertNotNull("comments type should be found", typeDeclaration);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("/**\n\tsome\n\tjavadoc\n*/leftover".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertTrue("Fragment should be an instance of CommentFragment", fragment instanceof CommentFragment);
		CommentFragment commentfragment = (CommentFragment) fragment;
		assertEquals("ID of the fragment should match", "Comment", commentfragment.getId());
		testCommentFragment(
				commentfragment,
				CommentFragment.Type.JavaDoc,
				"/**\n\tsome\n\tjavadoc\n*/",
				"<span class=\"javadoc\">/**\n\tsome\n\tjavadoc\n*/</span>",
				"\n\tsome\n\tjavadoc\n"
		);
		
		checkLeftover(sis, "leftover");
	}
}
