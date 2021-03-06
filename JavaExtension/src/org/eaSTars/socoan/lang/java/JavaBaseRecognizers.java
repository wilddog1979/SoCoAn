package org.eaSTars.socoan.lang.java;

import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.DefaultFragmentImpl;
import org.eaSTars.socoan.lang.LangRecognizers;
import org.eaSTars.socoan.lang.WrappedIOException;

public class JavaBaseRecognizers extends LangRecognizers {
	
	public boolean recognizeJavaLetter(Context context, SourcecodeInputReader sis) {
		int c = 0;
		try {
			c = sis.read();
		} catch (IOException e) {
			throw new WrappedIOException(e);
		}
		boolean result = Character.isJavaIdentifierStart(c);
		if (result) {
			DefaultFragmentImpl fragment = new DefaultFragmentImpl(context.getFormatProvider());
			fragment.setFragment(""+(char)c);
			fragment.setFormattedFragment(""+(char)c);
			context.push(fragment);
		} else {
			sis.unread((char)c);
		}
		
		return result;
	}
	
	public boolean recognizeJavaLetterOrDigit(Context context, SourcecodeInputReader sis) {
		int c = 0;
		try {
			c = sis.read();
		} catch (IOException e) {
			throw new WrappedIOException(e);
		}
		boolean result = Character.isJavaIdentifierPart(c);
		if (result) {
			DefaultFragmentImpl fragment = new DefaultFragmentImpl(context.getFormatProvider());
			fragment.setFragment(""+(char)c);
			fragment.setFormattedFragment(""+(char)c);
			context.push(fragment);
		} else {
			sis.unread((char)c);
		}
		
		return result;
	}
}
