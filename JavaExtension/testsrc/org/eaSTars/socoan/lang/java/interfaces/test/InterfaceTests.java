package org.eaSTars.socoan.lang.java.interfaces.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	NormalInterfaceDeclarationTest.class,
	FieldConstantTest.class,
	InterfaceMethodDeclarationTest.class,
	AnnotationTypeDeclarationTest.class,
	AnnotationTypeElementDeclarationTest.class,
	AnnotationTest.class
})
public class InterfaceTests {

}
