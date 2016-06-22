package org.eaSTars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PushbackInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eaSTars.socoan.Configuration;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ObjectFactory;

public class SoCoAn {

	private static void printHelp() {
		System.out.println("Usage: org.eaSTars.SoCoAn <configuration file>");
	}
	
	public static void main(String[] args) {
		System.out.println("Source Code Analyzer experimental project");
		Configuration configuration = new Configuration();
		if (configuration.processArguments(args)) {
			new SoCoAn(configuration);
		} else {
			printHelp();
		}
	}

	private SoCoAn(Configuration configuration) {
		System.out.println(configuration.getConfig().getProjectPath());
		
		try {
			PushbackInputStream pis = new PushbackInputStream(new FileInputStream("samplesrc/org/eaSTars/testsrc/SampleClass.java"));
			
			
			try {
				pis.close();
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(new File("resources/JavaLang.xml"));
			Language language = doc.getValue();
			
			System.out.println(language.getNodeTypes().size());
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}	
	}
}
