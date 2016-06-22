package org.eaSTars.socoan.lang.java.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ObjectFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	CommentNodeTest.class,
	WhitespaceTest.class,
	SeparatorTest.class
})
public class JavaTests {

	public static Language java;
	
	@BeforeClass
	public static void setupTestClass() throws Exception {
		JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(new File("resources/JavaLang.xml"));
		java = doc.getValue();
	}
}
