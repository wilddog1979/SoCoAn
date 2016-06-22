package org.eaSTars.socoan.lang.base;

import org.eaSTars.socoan.lang.LanguageFragment;

public class LiteralFragment implements LanguageFragment {

	private String fragment;
	private String content;

	@Override
	public String getFragment() {
		return fragment;
	}

	public void setFragment(String literal) {
		this.fragment = literal;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
