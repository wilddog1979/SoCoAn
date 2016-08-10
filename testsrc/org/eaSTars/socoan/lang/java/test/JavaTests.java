package org.eaSTars.socoan.lang.java.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.LanguageObjectFactory;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.lexical.test.JavaLexicalTests;
import org.eaSTars.socoan.lang.java.packages.test.PackagesTests;
import org.eaSTars.socoan.lang.java.types.test.JavaTypesTests;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	JavaLexicalTests.class,
	JavaTypesTests.class,
	PackagesTests.class
})
public class JavaTests {

private static Language javalang = null;
	
	@BeforeClass
	public static void initJavaLang() throws JAXBException, ReferenceNotFoundException {
		JAXBContext context = JAXBContext.newInstance(LanguageObjectFactory.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		@SuppressWarnings("unchecked")
		JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(new File("resources/JavaLang.xml"));
		javalang = doc.getValue();
		
		javalang.resolveFileReferences(new File("resources"));
		javalang.resolveNodeReferences(null);
	}
	
	public static Language getJavaLang() throws JAXBException, ReferenceNotFoundException {
		if (javalang == null) {
			initJavaLang();
		}
		return javalang; 
	}
}
