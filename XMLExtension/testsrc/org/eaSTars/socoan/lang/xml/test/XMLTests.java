package org.eaSTars.socoan.lang.xml.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;
import org.eaSTars.socoan.lang.xml.common.test.CommonTests;
import org.eaSTars.socoan.lang.xml.complex.test.ComplexTests;
import org.eaSTars.socoan.test.SoCoAnTests;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	SoCoAnTests.class,
	CommonTests.class,
	ComplexTests.class
})
public class XMLTests {

	private static Language xmllang = null;
	
	@BeforeClass
	public static void initXmlLang() throws JAXBException, ReferenceNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Language.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File xmllangfile = new File("resources/XMLLang.xml");
		JAXBElement<Language> doc = unmarshaller.unmarshal(new StreamSource(xmllangfile), Language.class);
		xmllang = doc.getValue();
		
		xmllang.resolveFileReferences(new File("resources"));
		xmllang.resolveNodeReferences(null);
	}
	
	public static Language getXmlLang() throws JAXBException, ReferenceNotFoundException {
		if (xmllang == null) {
			initXmlLang();
		}
		return xmllang; 
	}
}
