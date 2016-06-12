package org.eaSTars.socoan.lang.base;

import org.eaSTars.socoan.lang.LanguageFragment;

public class LiteralFragment implements LanguageFragment {

	private String literal;

	public LiteralFragment(String literal) {
		this.literal = literal;
	}
	
	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}
	
	@Override
	public String getContent() {
		return getLiteral();
	}
}
