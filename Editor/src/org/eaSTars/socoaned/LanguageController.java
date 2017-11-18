package org.eaSTars.socoaned;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.StyleContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;

public class LanguageController {

	private StyleContext sc = new StyleContext();
	private DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	private JTextPane languagePane = new JTextPane(doc);

	private File file;

	public LanguageController(File file) throws JAXBException, ReferenceNotFoundException, FileNotFoundException, IOException {
		this.file = file;
		
		File xmlLang = new File("../XMLExtension/resources/XMLLang.xml");
		JAXBContext context = JAXBContext.newInstance(Language.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Language> doc = unmarshaller.unmarshal(new StreamSource(xmlLang), Language.class);
		Language language = doc.getValue();
		
		language.resolveFileReferences(xmlLang.getParentFile());
		language.resolveNodeReferences(null);
		
		AbstractTypeDeclaration document = language.getTypeDeclaration("document");
		
		Context ctx = new Context(language);
		boolean result = document.recognizeType(ctx, new SourcecodeInputReader(new FileInputStream(file)));
		
		System.out.println(result);
		
		if (result) {
			Element element = this.doc.getDefaultRootElement();
			System.out.println(element);
		}
	}
	
	/*public LanguageController(File file)
			throws TransformerFactoryConfigurationError, TransformerException, IOException {
		this.file = file;
		
		StringBuffer sb = new StringBuffer();
		LineNumberReader lnr = new LineNumberReader(new FileReader(file));
		String buffer = null;
		while((buffer = lnr.readLine()) != null) {
			sb.append(buffer);
			sb.append('\n');
		}
		lnr.close();
		SwingUtilities.invokeLater(() -> {
			try {
				doc.insertString(0, sb.toString(), null);
			} catch (BadLocationException e) {}
		});

		StreamSource source = new StreamSource(file);
		DOMResult target = new DOMResult();

		TransformerFactory.newInstance().newTransformer()
		.transform(source, target);

		dumpDom(0, target.getNode());
	}

	private void dumpDom(int indent, Node node) {
		//for (int i = 0; i < indent; ++i) {
		//	System.out.print(" ");
		//}
		//System.out.print(String.format("%s %d", node.getNodeName(), node.getNodeType()));
		switch(node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
				dumpDom(indent + 4, child);
			}
			break;
		case Node.ELEMENT_NODE:
			if (node.hasChildNodes()) {
				if (node.hasAttributes()) {
					System.out.print(String.format("<%s", node.getNodeName()));
					NamedNodeMap attrs = node.getAttributes();
					for (int i = 0; i < attrs.getLength(); ++i) {
						Node attr = attrs.item(i);
						System.out.print(String.format(" %s=\"%s\"", attr.getNodeName(), attr.getNodeValue()));
					}
					System.out.print(">");
				} else {
					System.out.print(String.format("<%s>", node.getNodeName()));
				}
				for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
					dumpDom(indent + 4, child);
				}
				System.out.print(String.format("</%s>", node.getNodeName()));
			} else {
				System.out.print(String.format("<%s", node.getNodeName()));
				NamedNodeMap attrs = node.getAttributes();
				for (int i = 0; i < attrs.getLength(); ++i) {
					Node attr = attrs.item(i);
					System.out.print(String.format(" %s=\"%s\"", attr.getNodeName(), attr.getNodeValue()));
				}
				System.out.print("/>");
			}
			break;
		case Node.TEXT_NODE:
			System.out.print(node.getNodeValue());
			break;
		}
	}*/

	public JTextPane getLanguagePane() {
		return languagePane;
	}

	public File getFile() {
		return file;
	}
}
