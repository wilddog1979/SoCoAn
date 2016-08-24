package org.eaSTars.socoan.lang.java.types.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	PrimitiveTypeTest.class,
	ClassOrInterfaceTypeTest.class,
	TypeVariableTest.class,
	ArrayTypeTest.class,
	TypeParameterTest.class,
	TypeArgumentTest.class,
	AnnotationIdentifierTypeAgrumentTest.class
})
public class JavaTypesTests {
	
}
