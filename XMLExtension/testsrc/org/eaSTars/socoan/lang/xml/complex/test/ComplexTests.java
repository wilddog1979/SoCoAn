package org.eaSTars.socoan.lang.xml.complex.test;

import org.eaSTars.socoan.lang.xml.complex.fragment.test.FragmentTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	FragmentTests.class,
	CommentTest.class,
	PITest.class,
	CDSectTest.class,
	XMLDeclTest.class,
	EmptyElementTagTest.class,
	STagTest.class,
	ETagTest.class,
	ElementdeclTest.class,
	NotationDeclTest.class,
	EntityDeclTest.class,
	AttlistDeclTest.class,
	PrologTest.class,
	ElementTagTest.class
})
public class ComplexTests {

}
