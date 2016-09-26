package org.eaSTars.socoan.lang.java.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.java.arrays.test.ArraysTests;
import org.eaSTars.socoan.lang.java.blocksandstatements.test.BlocksAndStatementsTests;
import org.eaSTars.socoan.lang.java.classes.test.JavaClassesTests;
import org.eaSTars.socoan.lang.java.interfaces.test.InterfaceTests;
import org.eaSTars.socoan.lang.java.lexical.test.JavaLexicalTests;
import org.eaSTars.socoan.lang.java.names.test.JavaNamesTests;
import org.eaSTars.socoan.lang.java.packages.test.JavaPackagesTests;
import org.eaSTars.socoan.lang.java.types.test.JavaTypesTests;
import org.eaSTars.socoan.test.SoCoAnTests;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	SoCoAnTests.class,
	JavaLexicalTests.class,
	JavaTypesTests.class,
	JavaNamesTests.class,
	JavaPackagesTests.class,
	JavaClassesTests.class,
	InterfaceTests.class,
	ArraysTests.class,
	BlocksAndStatementsTests.class
})
public class JavaTests {

	private static Language javalang = null;
	
	@BeforeClass
	public static void initJavaLang() throws JAXBException, ReferenceNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Language.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File javalangfile = new File("resources/JavaLang.xml");
		JAXBElement<Language> doc = unmarshaller.unmarshal(new StreamSource(javalangfile), Language.class);
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
