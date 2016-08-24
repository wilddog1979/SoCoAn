package org.eaSTars.socoan.lang.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	LiteralTypeTest.class,
	TerminatedTypeTest.class,
	ComplexTypeTest.class,
	ComplexTypeEmbeddedNodesTest.class
})
public class LangTests {

}
