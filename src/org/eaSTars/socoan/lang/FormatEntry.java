package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class FormatEntry {

	@XmlAttribute(name="key")
	private String key;
	
	@XmlValue
	private String value;
	
	public FormatEntry() {
	}
	
	public FormatEntry(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
