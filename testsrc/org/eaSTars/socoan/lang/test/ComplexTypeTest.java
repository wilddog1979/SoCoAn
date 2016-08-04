package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.Occurrance;
import org.junit.Test;

public class ComplexTypeTest extends AbstractLangTest {

	@Test
	public void testSimpleMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("aleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testOptionalString("Fragment", "a", fragment.getFragment());
		testOptionalString("Formatted fragment", "a", fragment.getFormattedFragment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNodesMatch1() {
		/*
		 *      b
		 *    /   \
		 * -a      >-d
		 *    \   /
		 *      c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getNextNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getNextNodes().add(new NextNodeHelper(node_d));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abdleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "abd", "abd");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNodesMatch2() {
		/*
		 *      b
		 *    /   \
		 * -a      >-d
		 *    \   /
		 *      c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getNextNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getNextNodes().add(new NextNodeHelper(node_d));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("acdleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "acd", "acd");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}

	@Test
	public void testEmbeddedComplexNodesMatch() {
		/*
		 *  x: -a-b-
		 * 
		 *      x
		 *    /   \
		 * -c      e-
		 *    \   /
		 *      d
		 */
		ComplexTypeHelper xcomplextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		xcomplextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_x = new ComplexTypeNodeHelper(xcomplextype);
		node_c.getNextNodes().add(new NextNodeHelper(node_x));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		ComplexTypeNodeHelper node_e = new ComplexTypeNodeHelper(new LiteralTypeHelper("e"));
		node_x.getNextNodes().add(new NextNodeHelper(node_e));
		node_d.getNextNodes().add(new NextNodeHelper(node_e));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("cabeleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "cabe", "cabe");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNoMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("leftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNodesNoMatch1() {
		/*
		 *      b
		 *    /   \
		 * -a      >-d
		 *    \   /
		 *      c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getNextNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getNextNodes().add(new NextNodeHelper(node_d));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 10, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNodesNoMatch2() {
		/*
		 *      b
		 *    /   \
		 * -a      >-d
		 *    \   /
		 *      c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getNextNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getNextNodes().add(new NextNodeHelper(node_d));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("acleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 10, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testEmbeddedComplexNodesNoMatch() {
		/*
		 *  x: -a-b-
		 * 
		 *      x
		 *    /   \
		 * -c      e-
		 *    \   /
		 *      d
		 */
		ComplexTypeHelper xcomplextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		xcomplextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_x = new ComplexTypeNodeHelper(xcomplextype);
		node_c.getNextNodes().add(new NextNodeHelper(node_x));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getNextNodes().add(new NextNodeHelper(node_d));
		
		ComplexTypeNodeHelper node_e = new ComplexTypeNodeHelper(new LiteralTypeHelper("e"));
		node_x.getNextNodes().add(new NextNodeHelper(node_e));
		node_d.getNextNodes().add(new NextNodeHelper(node_e));
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("cableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 11, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroormore_a() {
		/*
		 * -a--{b}-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_b.setOccurrance(Occurrance.ZeroOrMore);
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("aleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "a", "a");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroormore_abbbb() {
		/*
		 * -a--{b}-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_b.setOccurrance(Occurrance.ZeroOrMore);
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abbbbleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "abbbb", "abbbb");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroormore_aaaab() {
		/*
		 * -{a}-b-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setOccurrance(Occurrance.ZeroOrMore);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("aaaableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "aaaab", "aaaab");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroormore_cb() {
		/*
		 * -{a}-b-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setOccurrance(Occurrance.ZeroOrMore);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("cbleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "cb", "cb");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroorone_a() {
		/*
		 * -a--[b]-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_b.setOccurrance(Occurrance.ZeroOrOne);
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("aleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "a", "a");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroorone_ab() {
		/*
		 * -a--{b}-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_b.setOccurrance(Occurrance.ZeroOrOne);
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("abbbbleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "ab", "ab");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 11, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroorone_b() {
		/*
		 * -[a]-b-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setOccurrance(Occurrance.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("bleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "b", "b");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroorone_ab_2() {
		/*
		 * -[a]-b-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setOccurrance(Occurrance.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("ableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "ab", "ab");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testzeroorone_cb() {
		/*
		 * -[a]-b-
		 *     /
		 * --c
		 */
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setOccurrance(Occurrance.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getNextNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("cbleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		testFragment(fragment, "cb", "cb");
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
}
