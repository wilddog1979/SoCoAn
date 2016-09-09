package org.eaSTars.socoan.lang;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	AbstractTypeDeclaration.class,
	Include.class
})
public abstract class AbstractBaseElement {

	private static final String[][] REPLACE_RULES = {
			{"(\\\\b)", "\b"},
			{"(\\\\t)", "\t"},
			{"(\\\\n)", "\n"},
			{"(\\\\f)", "\f"},
			{"(\\\\r)", "\r"},
			{"(\\\\\")", "\""},
			{"(\\\\')", "\'"},
			{"(\\\\\\\\)", "\\"}
	};
	private static final String[][] HTML_RULES = {
			{"&", "&amp;"},
			{"<", "&lt;"},
			{">", "&gt;"}
	};
	private boolean processed = false;
	
	public void resolveFileReferences(File location) throws JAXBException {
		
	}
	
	public void resolveFileReferences(
			Optional<FileReferenceListener> fileReferenceListener,
			File location)
					throws JAXBException {
		
	}
	
	public void resolveNodeReferences(Language parent)
			throws ReferenceNotFoundException {
		
	}

	public void resolveNodeReferences(
			Optional<TypeReferenceListener> typeReferenceListener, Language parent)
					throws ReferenceNotFoundException {
		
	}
	
	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	private String replaceInner(String[][] ruleset, String value) {
		for (String[] rule : ruleset) {
			value = value.replaceAll(rule[0], rule[1]);
		}
		
		return value;
	}
	
	protected String replaceCharacters(String value) {
		return replaceInner(REPLACE_RULES, value);
	}
	
	protected String applyHTMLRules(String value) {
		return replaceInner(HTML_RULES, value);
	}
}
