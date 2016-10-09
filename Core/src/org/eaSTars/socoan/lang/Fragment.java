package org.eaSTars.socoan.lang;

import java.util.Optional;

public interface Fragment {

	public String getId();
	
	public void setId(String id);
	
	public Optional<String> getFormattedFragment();
	
	public void setFormattedFragment(String formattedFragment);
	
	public Optional<String> getFragment();
	
	public void setFragment(String fragment);
}
