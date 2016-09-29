package org.eaSTars.socoan.lang.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.Language;
import org.junit.Test;

public class ComplexTypeEmbeddedNodesTest extends AbstractLangTest {

	@Test
	public void testSimpleMatch() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
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
		testOptionalString("Fragment", "ab", fragment.getFragment());
		testOptionalString("Formatted fragment", "ab", fragment.getFormattedFragment());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testMultiBranchMatch1() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getInnerNodes().add(node_d);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cdleftover".getBytes()));
		
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
		testOptionalString("Fragment", "cd", fragment.getFragment());
		testOptionalString("Formatted fragment", "cd", fragment.getFormattedFragment());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testMultiBranchMatch2() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_b.getInnerNodes().add(node_c);
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(node_d);
		
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
	public void testMultiBranchMatch3() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		node_b.getInnerNodes().add(node_c);
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_b.getInnerNodes().add(node_d);
		
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
		testOptionalString("Fragment", "abd", fragment.getFragment());
		testOptionalString("Formatted fragment", "abd", fragment.getFormattedFragment());
		
		checkLeftover(sis, "leftover");
	}
	
	@Test
	public void testMultiBranchNoMatch1() {
		ComplexTypeHelper complextype = new ComplexTypeHelper(context -> new LangProcessors().processAggregation(context));
		ComplexTypeNodeHelper node_a = new ComplexTypeNodeHelper(new LiteralTypeHelper("a"));
		complextype.getStartnodes().add(node_a);
		
		ComplexTypeNodeHelper node_b = new ComplexTypeNodeHelper(new LiteralTypeHelper("b"));
		node_a.getInnerNodes().add(node_b);
		
		ComplexTypeNodeHelper node_c = new ComplexTypeNodeHelper(new LiteralTypeHelper("c"));
		complextype.getStartnodes().add(node_c);
		
		ComplexTypeNodeHelper node_d = new ComplexTypeNodeHelper(new LiteralTypeHelper("d"));
		node_c.getInnerNodes().add(node_d);
		
		SourcecodeInputReader sis = new SourcecodeInputReader(new ByteArrayInputStream("cleftover".getBytes()));
		
		Context context = new Context((Language)null);
		
		boolean testresult = false;
		try {
			testresult = complextype.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		
		assertFalse("Sample should not be recognized", testresult);
		assertEquals("Context buffer should not contain any entries", 0, context.size());
		
		checkLeftover(sis, "cleftover");
	}
}
