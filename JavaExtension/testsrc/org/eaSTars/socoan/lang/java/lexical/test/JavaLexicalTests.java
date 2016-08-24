package org.eaSTars.socoan.lang.java.lexical.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	UnicodeInputCharacterTest.class,
	CommentTypeTest.class,
	SeparatorTypeTest.class,
	IdentifierTest.class,
	DecimalIntegerLiteralTest.class,
	HexIntegerLiteralTest.class,
	OctalIntegerLiteralTest.class,
	BinaryIntegerLiteralTest.class,
	DecimalFloatingPointLiteralTest.class,
	HexadecimalFloatingPointLiteralTest.class,
	CharacterLiteralTest.class,
	StringLiteralTest.class
})
public class JavaLexicalTests {

}
