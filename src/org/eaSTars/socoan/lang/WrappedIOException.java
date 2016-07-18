package org.eaSTars.socoan.lang;

import java.io.IOException;

public class WrappedIOException extends RuntimeException {

	private static final long serialVersionUID = 314529945184793442L;

	private IOException ioexception;
	
	public WrappedIOException(IOException ioexception) {
		this.ioexception = ioexception;
	}

	public IOException getIoexception() {
		return ioexception;
	}
}
