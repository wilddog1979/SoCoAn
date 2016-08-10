package org.eaSTars.socoan.lang.java.packages.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PackageDeclarationTest.class,
	ImportDeclarationTest.class,
	TypeParametersTest.class,
	SuperclassTest.class,
	SuperinterfacesTest.class
})
public class PackagesTests {

}
