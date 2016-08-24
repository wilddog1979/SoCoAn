package org.eaSTars.socoan.test;

import org.eaSTars.socoan.lang.test.LangTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		SourcecodeInputStreamTest.class,
		LangTests.class
	})
public class SoCoAnTests {
	
}
