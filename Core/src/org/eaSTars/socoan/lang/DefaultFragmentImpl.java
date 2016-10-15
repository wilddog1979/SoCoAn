package org.eaSTars.socoan.lang;

import java.util.Optional;

public class DefaultFragmentImpl implements Fragment {

	protected FormatProvider formatProvider;
	
	private String id;
	
	private Optional<String> formattedFragment = Optional.empty();
	
	private Optional<String> fragment = Optional.empty();

	public DefaultFragmentImpl(FormatProvider formatProvider) {
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
