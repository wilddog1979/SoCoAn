package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Sequence {

	Any,
	Order;
	
	public String value() {
		return name();
	}
	
	public static Sequence fromValue(String value) {
		return valueOf(value);
	}
}
