package org.eaSTars.socoan.lang;

public class KeywordType extends LiteralType {

	@Override
	protected Fragment createFragment(Context context, String content) {
		KeywordFragment fragment = new KeywordFragment(context.getFormatProvider());
		fragment.setFragment(content);
		fragment.setKeyword(content);
		return fragment;
	}
}
