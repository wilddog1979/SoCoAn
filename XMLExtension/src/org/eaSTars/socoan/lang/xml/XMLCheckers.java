package org.eaSTars.socoan.lang.xml;

import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangCheckers;
import org.eaSTars.socoan.lang.Language;

public class XMLCheckers extends LangCheckers {

	public boolean checkChar(Language language, Fragment fragment) {
		//#x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c == 0x9 ||
				c == 0xa ||
				c == 0xd ||
				(c >= 0x20 && c <= 0x0d7ff) ||
				(c >= 0x0e000 && c <= 0x0fffd) ||
				(c >= 0x010000 && c <= 0x010ffff);
	}
	
	public boolean checkNameStartChar(Language language, Fragment fragment) {
		//":" | [A-Z] | "_" | [a-z] | [#xC0-#xD6] | [#xD8-#xF6] | [#xF8-#x2FF] | [#x370-#x37D] | [#x37F-#x1FFF] |
		//[#x200C-#x200D] | [#x2070-#x218F] | [#x2C00-#x2FEF] | [#x3001-#xD7FF] | [#xF900-#xFDCF] | [#xFDF0-#xFFFD] |
		//[#x10000-#xEFFFF]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c == ':' ||
				(c >= 'A' && c <= 'Z') ||
				c == '_' ||
				(c >= 'a' && c <= 'z') ||
				(c >= 0x0c0 && c <= 0x0d6) ||
				(c >= 0x0d8 && c <= 0x0f6) ||
				(c >= 0x0f8 && c <= 0x02ff) ||
				(c >= 0x0370 && c <= 0x037d) ||
				(c >= 0x037f && c >= 0x01fff) ||
				(c >= 0x0200c && c >= 0x0200d) ||
				(c >= 0x02070 && c >= 0x0218f) ||
				(c >= 0x02c00 && c >= 0x02fef) ||
				(c >= 0x03001 && c >= 0x0d7ff) ||
				(c >= 0x0f900 && c >= 0x0fdcf) ||
				(c >= 0x0fdf0 && c >= 0x0fffd) ||
				(c >= 0x010000 && c>= 0x0effff);
	}
	
	public boolean checkNameChar(Language language, Fragment fragment) {
		//"-" | "." | [0-9] | #xB7 | [#x0300-#x036F] | [#x203F-#x2040]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c == '-' ||
				c == '.' ||
				(c >= '0' && c <= '9') ||
				c == 0x0b7 ||
				(c >= 0x0300 && c <= 0x036f) ||
				(c >= 0x0203f && c <= 0x02040);
	}
	
	public boolean checkNotPercentAndDoubleQuote(Language language, Fragment fragment) {
		//[^%&"]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c != '%' &&
				c != '&' &&
				c != '"';
	}
	
	public boolean checkNotPercentAndSingleQuote(Language language, Fragment fragment) {
		//[^%&']
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c != '%' &&
				c != '&' &&
				c != '\'';
	}
	
	public boolean checkNotLowerAndDoubleQuote(Language language, Fragment fragment) {
		//[^<&"]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c != '<' &&
				c != '&' &&
				c != '"';
	}
	
	public boolean checkNotLowerAndSingleQuote(Language language, Fragment fragment) {
		//[^<&"]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c != '<' &&
				c != '&' &&
				c != '\'';
	}
	
	public boolean checkNotDoubleQuote(Language language, Fragment fragment) {
		//[^"]
		char c = fragment.getFragment().get().charAt(0);
		
		return c != '"';
	}
	
	public boolean checkNotSingleQuote(Language language, Fragment fragment) {
		//[^"]
		char c = fragment.getFragment().get().charAt(0);
		
		return c != '\'';
	}
	
	public boolean checkPubidChar(Language language, Fragment fragment) {
		//#x20 | #xD | #xA | [a-zA-Z0-9] | [-()+,./:=?;!*#@$_%]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c == 0x20 ||
				c == 0xd ||
				c == 0xa ||
				(c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') ||
				c == '-' ||
				c =='(' ||
				c ==')' ||
				c =='+' ||
				c ==',' ||
				c =='.' ||
				c =='/' ||
				c ==':' ||
				c =='=' ||
				c =='?' ||
				c ==';' ||
				c =='!' ||
				c =='*' ||
				c =='#' ||
				c =='@' ||
				c =='$' ||
				c =='_' ||
				c =='%'
				;
	}
	
	public boolean checkNotLowerAnd(Language language, Fragment fragment) {
		//[^<&]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				c != '<' &&
				c != '&';
	}
	
	public boolean checkCharData(Language language, Fragment fragment) {
		return fragment.getFragment().get().indexOf("]]>") == -1;
	}
	
	public boolean checkCharWithoutDash(Language language, Fragment fragment) {
		char c = fragment.getFragment().get().charAt(0);
		
		return c != '-';
	}
	
	public boolean checkPITarget(Language language, Fragment fragment) {
		return fragment.getFragment().get().toLowerCase().indexOf("xml") == -1;
	}
	
	public boolean checkCharWithoutPIEnd(Language language, Fragment fragment) {
		return fragment.getFragment().get().indexOf("?>") == -1;
	}
	
	public boolean checkEncNameFirstChar(Language language, Fragment fragment) {
		//[A-Za-z]
		char c = fragment.getFragment().get().charAt(0);
		
		return
				(c >= 'A' && c <= 'Z') ||
				(c >= 'a' && c <= 'z');
	}
	
	public boolean checkEncNameChar(Language language, Fragment fragment) {
		//[A-Za-z0-9._] | '-'
		char c = fragment.getFragment().get().charAt(0);
		
		return
				(c >= 'A' && c <= 'Z') ||
				(c >= 'a' && c <= 'z') ||
				(c >= '0' && c <= '9') ||
				c == '.' ||
				c == '_' ||
				c == '-';
	}
}
