package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.ComplexNode;
import org.eaSTars.socoan.lang.LanguageContext;
import org.eaSTars.socoan.lang.LanguageFragment;
import org.eaSTars.socoan.lang.NodeRef;
import org.eaSTars.socoan.lang.base.Literal;
import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.junit.Test;

public class ComplexNodeTest {


	@Test
	public void testRecognizeSimpleGraphMatch() {
		/*
		 * -a-b-
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ab".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeSimpleGraphMatchLongerSample() {
		/*
		 * -a-b-
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeSimpleGraphNoMatch() {
		/*
		 * -a-b-
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ac".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeTwoRoutes1() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ab".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeTwoRoutes1LongerSample() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeTwoRoutes2() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ac".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeTwoRoutes2LongerSample() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("acb".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeTwoRoutesNoMatch() {
		/*     b
		 *    / \
		 * -a    >-
		 *    \ /
		 *     c
		 */
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		Literal literal_b = new Literal("b");
		literal_a.getNextNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_a.getNextNodes().add(new NodeRef(literal_c));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ad".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
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
	public void testRecognizeEmbedded1() {
		/*
		 * 
		 *  o = -b-c-
		 * 
		 *    _o_
		 *   /   \
		 * -a     >-
		 *   \_ _/
		 *     d
		 */
		ComplexNode subgraph = new ComplexNode();
		
		Literal literal_b = new Literal("b");
		subgraph.getStartNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_b.getNextNodes().add(new NodeRef(literal_c));
		
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		literal_a.getNextNodes().add(new NodeRef(subgraph));
		
		Literal literal_d = new Literal("d");
		literal_a.getNextNodes().add(new NodeRef(literal_d));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abc".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "bc", literalFragment1.getFragment());
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
	public void testRecognizeEmbedded2() {
		/*
		 * 
		 *  o = -b-c-
		 * 
		 *    _o_
		 *   /   \
		 * -a     >-
		 *   \_ _/
		 *     d
		 */
		ComplexNode subgraph = new ComplexNode();
		
		Literal literal_b = new Literal("b");
		subgraph.getStartNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_b.getNextNodes().add(new NodeRef(literal_c));
		
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		literal_a.getNextNodes().add(new NodeRef(subgraph));
		
		Literal literal_d = new Literal("d");
		literal_a.getNextNodes().add(new NodeRef(literal_d));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ad".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 2 entries", 2, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "d", literalFragment1.getFragment());
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
	public void testRecognizeEmbedded3() {
		/*
		 * 
		 *  o = -b-c-
		 * 
		 *    _o_
		 *   /   \
		 * -a     >-e-
		 *   \_ _/
		 *     d
		 */
		ComplexNode subgraph = new ComplexNode();
		
		Literal literal_b = new Literal("b");
		subgraph.getStartNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_b.getNextNodes().add(new NodeRef(literal_c));
		
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		literal_a.getNextNodes().add(new NodeRef(subgraph));
		
		Literal literal_d = new Literal("d");
		literal_a.getNextNodes().add(new NodeRef(literal_d));
		
		Literal literal_e = new Literal("e");
		subgraph.getNextNodes().add(new NodeRef(literal_e));
		literal_d.getNextNodes().add(new NodeRef(literal_e));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abce".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognizeNode(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain 3 entries", 3, context.size());
		
		LanguageFragment fragment1 = context.pop();
		assertTrue("1st entry in context buffer should be an instance of LiteralFragment", fragment1 instanceof LiteralFragment);
		LiteralFragment literalFragment1 = (LiteralFragment) fragment1;
		assertEquals("1st LiteralFragment should contain the matched sample", "e", literalFragment1.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment1.getContent() == null);
		
		LanguageFragment fragment2 = context.pop();
		assertTrue("2nd entry in context buffer should be an instance of LiteralFragment", fragment2 instanceof LiteralFragment);
		LiteralFragment literalFragment2 = (LiteralFragment) fragment2;
		assertEquals("2nd LiteralFragment should contain the matched sample", "bc", literalFragment2.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment2.getContent() == null);
		
		LanguageFragment fragment3 = context.pop();
		assertTrue("3rd entry in context buffer should be an instance of LiteralFragment", fragment3 instanceof LiteralFragment);
		LiteralFragment literalFragment3 = (LiteralFragment) fragment3;
		assertEquals("3rd LiteralFragment should contain the matched sample", "a", literalFragment3.getFragment());
		assertTrue("Content of LiteralFragment should be null", literalFragment3.getContent() == null);
		
		try {
			assertEquals("InputStream should be empty", 0, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeEmbeddedNoMatch1() {
		/*
		 * 
		 *  o = -b-c-
		 * 
		 *    _o_
		 *   /   \
		 * -a     >-
		 *   \_ _/
		 *     d
		 */
		ComplexNode subgraph = new ComplexNode();
		
		Literal literal_b = new Literal("b");
		subgraph.getStartNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_b.getNextNodes().add(new NodeRef(literal_c));
		
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		literal_a.getNextNodes().add(new NodeRef(subgraph));
		
		Literal literal_d = new Literal("d");
		literal_a.getNextNodes().add(new NodeRef(literal_d));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abd".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 3, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
	
	@Test
	public void testRecognizeEmbeddedNoMatch2() {
		/*
		 * 
		 *  o = -b-c-
		 * 
		 *    _o_
		 *   /   \
		 * -a     >-e-
		 *   \_ _/
		 *     d
		 */
		ComplexNode subgraph = new ComplexNode();
		
		Literal literal_b = new Literal("b");
		subgraph.getStartNodes().add(new NodeRef(literal_b));
		
		Literal literal_c = new Literal("c");
		literal_b.getNextNodes().add(new NodeRef(literal_c));
		
		ComplexNode testgraph = new ComplexNode();
		
		Literal literal_a = new Literal("a");
		testgraph.getStartNodes().add(new NodeRef(literal_a));
		
		literal_a.getNextNodes().add(new NodeRef(subgraph));
		
		Literal literal_d = new Literal("d");
		literal_a.getNextNodes().add(new NodeRef(literal_d));
		
		Literal literal_e = new Literal("e");
		subgraph.getNextNodes().add(new NodeRef(literal_e));
		literal_d.getNextNodes().add(new NodeRef(literal_e));
		
		LanguageContext context = new LanguageContext(null);
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abcd".getBytes()));
		
		boolean testresult = false;
		try {
			testresult = testgraph.recognize(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception " + e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("InputStream should contain the rolled back elements", 4, sis.available());
		} catch (IOException e) {
			fail("Available method invocation failed " + e.getMessage());
		}
	}
}
