package org.eaSTars.socoan.lang.test;

import org.eaSTars.socoan.lang.base.test.LangBaseTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		LangBaseTests.class,
		ChainedLanguageNodeTest.class,
		ComplexNodeTest.class
	})
public class LangTests {

}
