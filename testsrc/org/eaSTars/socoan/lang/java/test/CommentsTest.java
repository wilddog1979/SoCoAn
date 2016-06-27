package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.junit.Test;

public class CommentsTest {

	@Test
	public void testRecognizeLineComment() {
		AbstractTypeDeclaration comments = JavaTests.getJavaLang().getTypeDeclaration("comments");
		
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
		assertEquals("Fragment should be equal to the recognized piece", "//some comment\n", fragment.getFragment());
		assertEquals("Fragment should contain content", "some comment" , fragment.getContent());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}

	@Test
	public void testRecognizeBlockComment() {
		AbstractTypeDeclaration comments = JavaTests.getJavaLang().getTypeDeclaration("comments");
		
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
		assertEquals("Fragment should be equal to the recognized piece", "/*\n\tsome\n\tcomment\n*/", fragment.getFragment());
		assertEquals("Fragment should contain content", "\n\tsome\n\tcomment\n" , fragment.getContent());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
