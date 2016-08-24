package org.eaSTars.socoan.lang;

import java.util.Optional;

public class KeywordFragment extends Fragment {

	private final static String FORMAT_KEY = "KEYWORD_FORMAT";
	
	private final static String DEFAULT_KEYWORD_FORMAT = "<span class=\"keyword\">%s</span>";
	
	private String keyword;

	public KeywordFragment(FormatProvider formatProvider) {
		super(formatProvider);
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public Optional<String> getFormattedFragment() {
		return Optional.ofNullable(String.format(formatProvider.getFormat(FORMAT_KEY, DEFAULT_KEYWORD_FORMAT), keyword));
	}
}
