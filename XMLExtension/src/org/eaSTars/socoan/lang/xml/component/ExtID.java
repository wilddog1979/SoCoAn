package org.eaSTars.socoan.lang.xml.component;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.DefaultFragmentImpl;

public class ExtID extends DefaultFragmentImpl {

	public enum Type {
		System, Public
	}
	
	private Type type;
	
	private String pubidLiteral;
	
	private String systemLiteral;
	
	public ExtID(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPubidLiteral() {
		return pubidLiteral;
	}

	public void setPubidLiteral(String pubidLiteral) {
		this.pubidLiteral = pubidLiteral;
	}

	public String getSystemLiteral() {
		return systemLiteral;
	}

	public void setSystemLiteral(String systemLiteral) {
		this.systemLiteral = systemLiteral;
	}

}
