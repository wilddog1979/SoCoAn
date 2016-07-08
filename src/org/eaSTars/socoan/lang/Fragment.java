package org.eaSTars.socoan.lang;

import java.util.Optional;

public class Fragment {

	private String id;
	
	private Optional<String> formattedFragment;
	
	private Optional<String> fragment;

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
}
