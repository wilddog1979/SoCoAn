package org.eaSTars.test;

import org.eaSTars.socoan.lang.test.LangTests;
import org.eaSTars.socoan.test.SoCoAnTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		SoCoAnTests.class,
		LangTests.class
	})
public class SoCoAnAllTests {

}
