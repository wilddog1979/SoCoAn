package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Occurrence {

	Single,
	ZeroOrMore,	// {}
	ZeroOrOne;	// []
	
	public String value() {
		return name();
	}
	
	public static Occurrence fromValue(String value) {
		return valueOf(value);
	}
}
