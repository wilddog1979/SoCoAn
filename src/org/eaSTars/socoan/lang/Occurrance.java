package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Occurrance {

	Single,
	ZeroOrMore,	// {}
	ZeroOrOne;	// []
	
	public String value() {
		return name();
	}
	
	public static Occurrance fromValue(String value) {
		return valueOf(value);
	}
}
