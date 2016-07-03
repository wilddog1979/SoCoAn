package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LiteralType;

public class KeywordType extends LiteralType {

	@Override
	protected Fragment createFragment(String content) {
		KeywordFragment fragment = new KeywordFragment();
		fragment.setFragment(content);
		fragment.setKeyword(content);
		return fragment;
	}
}
