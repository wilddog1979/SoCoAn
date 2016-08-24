package org.eaSTars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eaSTars.socoan.Configuration;
import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.LanguageObjectFactory;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;

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
		File javalang = new File("resources/JavaLang.xml");
		
		try {
			JAXBContext context = JAXBContext.newInstance(LanguageObjectFactory.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<Language> doc = (JAXBElement<Language>) unmarshaller.unmarshal(javalang);
			Language language = doc.getValue();
			
			language.setFilename(javalang.getAbsolutePath());
			language.resolveFileReferences(new File("resources"));
			language.resolveNodeReferences(null);
			
			System.out.println(language);
			
			try {
				String filename = "samplesrc/org/eaSTars/testsrc/SampleClass.java";
				SourcecodeInputStream sis = new SourcecodeInputStream(new FileInputStream(filename));
				
				AbstractTypeDeclaration javafile = language.getTypeDeclaration("JavaTestJavaFile");
				
				Context pcontext = new Context(language);
				try {
					javafile.recognizeType(pcontext, sis);
					
					FileOutputStream fout = new FileOutputStream("samplesrc/testout.html");
					
					fout.write("<html>\n\t<head>\n\t</head>\n\t<style>\n\t\t.blockcomment {color:#3F7F5F;}\n\t\t.linecomment {color:#3F7F5F;}\n\t\t.javadoc {color:#3F5FBF;}\n\t\t.keyword {color:#7F0055;font-weight: bold;}\n\t</style>\n\t<body>\n\t\t<h1>".getBytes());
					fout.write(filename.getBytes());
					fout.write("</h1>\n\t\t<div>\n\t\t\t<hr/>\n\t\t\t<pre>".getBytes());
					
					Optional<String> formattedcontent = pcontext.pop().getFormattedFragment();
					if (formattedcontent.isPresent()) {
						fout.write(formattedcontent.get().getBytes());
					}
					
					fout.write("<br/></pre>\n\t\t\t<hr/>\n\t\t</div>\n\t</body>\n</html>\n".getBytes());
					
					fout.close();
				} catch (IOException e) {
				}
				
				try {
					sis.close();
				} catch (IOException e) {
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ReferenceNotFoundException e) {
			e.printStackTrace();
		}
	}
}
