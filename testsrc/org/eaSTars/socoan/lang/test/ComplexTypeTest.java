package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AggregatingType;
import org.eaSTars.socoan.lang.ComplexType;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.junit.Test;

public class ComplexTypeTest {

	@Test
	public void testSimpleMatch() {
		ComplexType complextype = new AggregatingType();
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("aleftover".getBytes()));
		
		Context context = new Context();
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertEquals("Fragment should be equal to the recognized piece", "a", fragment.getFragment());
		assertEquals("Formatted fragment should be equal to the recognized piece", "a", fragment.getFormattedFragment());
		
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
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertEquals("Fragment should be equal to the recognized piece", "abd", fragment.getFragment());
		assertEquals("Formatted fragment should be equal to the recognized piece", "abd", fragment.getFormattedFragment());
		
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
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertEquals("Fragment should be equal to the recognized piece", "acd", fragment.getFragment());
		assertEquals("Formatted fragment should be equal to the recognized piece", "acd", fragment.getFormattedFragment());
		
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
		ComplexType xcomplextype = new AggregatingType();
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		xcomplextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertTrue("Sample should be recognized", testresult);
		assertEquals("Context buffer should contain one entry", 1, context.size());
		Fragment fragment = context.pop();
		assertEquals("Fragment should be equal to the recognized piece", "cabe", fragment.getFragment());
		assertEquals("Formatted fragment should be equal to the recognized piece", "cabe", fragment.getFormattedFragment());
		
		try {
			assertEquals("The input stream should contain the leftover characters", 8, sis.available());
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	@Test
	public void testSimpleNoMatch() {
		ComplexType complextype = new AggregatingType();
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream("leftover".getBytes()));
		
		Context context = new Context();
		
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
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
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
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
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
		ComplexType xcomplextype = new AggregatingType();
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		xcomplextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getNextNodes().add(new NextNodeHelper(node_b));
		
		ComplexType complextype = new AggregatingType();
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
		
		Context context = new Context();
		
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
}
