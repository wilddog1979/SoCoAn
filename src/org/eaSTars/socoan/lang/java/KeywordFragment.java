package org.eaSTars.socoan.lang.java;

import java.util.Optional;

import org.eaSTars.socoan.lang.Fragment;

public class KeywordFragment extends Fragment {

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public Optional<String> getFormattedFragment() {
		return Optional.ofNullable(String.format(JavaFormat.KEYWORD_FORMAT, keyword));
	}
}
