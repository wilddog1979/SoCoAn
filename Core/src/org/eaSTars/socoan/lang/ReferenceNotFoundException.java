package org.eaSTars.socoan.lang;

public class ReferenceNotFoundException extends Exception {

	private static final long serialVersionUID = -5727335575331192953L;

	public ReferenceNotFoundException(String type, String reference) {
		super(type + " : " + reference);
	}
	
	public ReferenceNotFoundException(String filename, String type, String reference) {
		super(filename+" -> " + type + " : " + reference);
	}
}
