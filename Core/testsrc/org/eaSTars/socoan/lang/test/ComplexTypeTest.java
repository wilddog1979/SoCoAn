package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.Occurrence;
import org.eaSTars.socoan.lang.Sequence;
import org.junit.Test;

public class ComplexTypeTest extends AbstractLangTest {

	@Test
	public void testSimpleMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("aleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(new NextNodeHelper(node_d));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("abdleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(new NextNodeHelper(node_d));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("acdleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_x = new ComplexTypeNodeHelper(xcomplextype);
		node_c.getInnerNodes().add(new NextNodeHelper(node_x));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		ComplexTypeNodeHelper node_e = new ComplexTypeNodeHelper(new LiteralTypeHelper("e"));
		node_x.getInnerNodes().add(new NextNodeHelper(node_e));
		node_d.getInnerNodes().add(new NextNodeHelper(node_e));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cabeleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testSimpleNoMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("leftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "leftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(new NextNodeHelper(node_d));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("ableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "ableftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_c));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(new NextNodeHelper(node_d));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("acleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "acleftover");
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
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_x = new ComplexTypeNodeHelper(xcomplextype);
		node_c.getInnerNodes().add(new NextNodeHelper(node_x));
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_d));
		
		ComplexTypeNodeHelper node_e = new ComplexTypeNodeHelper(new LiteralTypeHelper("e"));
		node_x.getInnerNodes().add(new NextNodeHelper(node_e));
		node_d.getInnerNodes().add(new NextNodeHelper(node_e));
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample not should be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "cableftover");
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
		node_b.setOccurrence(Occurrence.ZeroOrMore);
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("aleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_b.setOccurrence(Occurrence.ZeroOrMore);
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("abbbbleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.setOccurrence(Occurrence.ZeroOrMore);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("aaaableftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.setOccurrence(Occurrence.ZeroOrMore);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cbleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_b.setOccurrence(Occurrence.ZeroOrOne);
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("aleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_b.setOccurrence(Occurrence.ZeroOrOne);
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("abbbbleftover".getBytes()));
		
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
		
		checkLeftover(sis, "bbbleftover");
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
		node_a.setOccurrence(Occurrence.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("bleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.setOccurrence(Occurrence.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("ableftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
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
		node_a.setOccurrence(Occurrence.ZeroOrOne);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(new NextNodeHelper(node_b));
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_c.getInnerNodes().add(new NextNodeHelper(node_b));
		complextype.getStartnodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cbleftover".getBytes()));
		
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
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testSequenceMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setSequence(Sequence.Order);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("abcleftover".getBytes()));
		
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
		testOptionalString("Fragment", "abc", fragment.getFragment());
		testOptionalString("Formatted fragment", "abc", fragment.getFormattedFragment());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testSequenceNoMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		node_a.setSequence(Sequence.Order);
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_a.getInnerNodes().add(node_c);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("ableftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "ableftover");
	}
}
