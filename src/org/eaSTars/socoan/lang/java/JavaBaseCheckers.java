package org.eaSTars.socoan.lang.java;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangCheckers;
import org.eaSTars.socoan.lang.Language;

public class JavaBaseCheckers extends LangCheckers {
	
	private boolean isMatching(Language language, AbstractTypeDeclaration type, Fragment fragment) {
		boolean result = false;
		SourcecodeInputStream sis = new SourcecodeInputStream(new ByteArrayInputStream(fragment.getFragment().get().getBytes()));
		try {
			result = type.recognizeType(new Context(language), sis) && sis.available() == 0;
		} catch (IOException e) {
			result = false;
		}
		return result;
	}
	
	public boolean checkIdentifierChars(Language language, Fragment fragment) {
		AbstractTypeDeclaration keywordtype = language.getTypeDeclaration("Keyword");
		AbstractTypeDeclaration booleanliteraltype = language.getTypeDeclaration("BooleanLiteral");
		AbstractTypeDeclaration nullliteraltype = language.getTypeDeclaration("NullLiteral");
		
		return
				isMatching(language, keywordtype, fragment) ||
				isMatching(language, booleanliteraltype, fragment) ||
				isMatching(language, nullliteraltype, fragment);
	}
}
