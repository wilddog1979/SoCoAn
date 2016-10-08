package org.eaSTars.socoan.lang;

import java.util.Optional;

public class Fragment {

	protected FormatProvider formatProvider;
	
	private String id;
	
	private Optional<String> formattedFragment;
	
	private Optional<String> fragment;

	public Fragment(FormatProvider formatProvider) {
		this.formatProvider = formatProvider;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Optional<String> getFormattedFragment() {
		return formattedFragment;
	}

	public void setFormattedFragment(String formattedFragment) {
		this.formattedFragment = Optional.ofNullable(formattedFragment);
	}

	public Optional<String> getFragment() {
		return fragment;
	}

	public void setFragment(String fragment) {
		this.fragment = Optional.ofNullable(fragment);
	}
	
	@Override
	public String toString() {
		return String.format("[ID: %s, fragment: %s]", this.getId(), this.getFragment());
	}
}
