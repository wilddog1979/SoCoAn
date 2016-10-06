package org.eaSTars.socoan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;

public class XMLTest {

	public static void main(String[] args) {
		File xmllang = new File("resources/XMLLang.xml");
		
		try {
			JAXBContext context = JAXBContext.newInstance(Language.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<Language> doc = unmarshaller.unmarshal(new StreamSource(xmllang), Language.class);
			Language language = doc.getValue();
			
			System.out.println(language);
			
			language.resolveFileReferences(new File("resources"));
			language.resolveNodeReferences(null);
			
			AbstractTypeDeclaration document = language.getTypeDeclaration("document");
			
			try {
				Context ctx = new Context(language);
				boolean result = document.recognizeType(ctx, new SourcecodeInputReader(new FileInputStream(xmllang)));
				
				System.out.println(result);
				
				System.out.println(ctx.size());
				ctx.forEach(fragment -> fragment.getFragment().ifPresent(content -> System.out.println(content)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ReferenceNotFoundException e) {
			e.printStackTrace();
		}
	}

}
