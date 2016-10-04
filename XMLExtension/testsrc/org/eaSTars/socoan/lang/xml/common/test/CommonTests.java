package org.eaSTars.socoan.lang.xml.common.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	WhiteSpaceTest.class,
	CharTest.class,
	EntityValueTest.class,
	AttValueTest.class,
	SystemLiteralTest.class,
	PubidLiteralTest.class,
	CharDataTest.class
})
public class CommonTests {

}
