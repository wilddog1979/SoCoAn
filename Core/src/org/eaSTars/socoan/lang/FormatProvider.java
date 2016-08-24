package org.eaSTars.socoan.lang;

import java.util.Optional;

public interface FormatProvider {

	public Optional<String> getFormat(String key);
	
	public String getFormat(String key, String defaultvalue);
}
