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
	
	public boolean checkInputCharacter(Language language, Fragment fragment) {
		AbstractTypeDeclaration CRtype = language.resolveRecursiveTypeDeclaration("CR");
		AbstractTypeDeclaration LFtype = language.resolveRecursiveTypeDeclaration("LF");
		
		return
				isMatching(language, CRtype, fragment) ||
				isMatching(language, LFtype, fragment);
	}
	
	public boolean checkIdentifierChars(Language language, Fragment fragment) {
		AbstractTypeDeclaration keywordtype = language.resolveRecursiveTypeDeclaration("Keyword");
		AbstractTypeDeclaration booleanliteraltype = language.resolveRecursiveTypeDeclaration("BooleanLiteral");
		AbstractTypeDeclaration nullliteraltype = language.resolveRecursiveTypeDeclaration("NullLiteral");
		
		return
				isMatching(language, keywordtype, fragment) ||
				isMatching(language, booleanliteraltype, fragment) ||
				isMatching(language, nullliteraltype, fragment);
	}
	
	public boolean checkStringCharacters(Language language, Fragment fragment) {
		AbstractTypeDeclaration doublequotetype = language.resolveRecursiveTypeDeclaration("DoubleQuote");
		AbstractTypeDeclaration backslashtype = language.resolveRecursiveTypeDeclaration("Backslash");
		
		return
				isMatching(language, doublequotetype, fragment) ||
				isMatching(language, backslashtype, fragment);
	}
	
	public boolean checkSingleCharacter(Language language, Fragment fragment) {
		AbstractTypeDeclaration singlequotetype = language.resolveRecursiveTypeDeclaration("SingleQuote");
		AbstractTypeDeclaration backslashtype = language.resolveRecursiveTypeDeclaration("Backslash");
		
		return
				isMatching(language, singlequotetype, fragment) ||
				isMatching(language, backslashtype, fragment);
	}
}
