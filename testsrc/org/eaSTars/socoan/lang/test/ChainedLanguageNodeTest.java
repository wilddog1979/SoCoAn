package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.NodeRef;
import org.eaSTars.socoan.lang.base.Literal;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class ChainedLanguageNodeTest {

	@Test
	public void testSimpleMatch() {
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ab".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "b", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}

	@Test
	public void testSimpleMatchLongerSample() {
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "b", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should contain the leftoer element", 1, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNoMatch() {
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ac".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 2, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testTwoRoutes1() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ab".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "b", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testTwoRoutes1LongerSample() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "b", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should contain the leftover element", 1, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testTwoRoutes2() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ac".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "c", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testTwoRoutes2LongerSample() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("acb".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "c", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "a", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		try {
			assertEquals("InputStream should contain the leftover element", 1, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testTwoRoutesNoMatch() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		Literal literal_a = new Literal("a");
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ad".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = literal_a.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 2, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
