package org.eaSTars.socoan.lang;

import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputReader;

public class LangRecognizers {

	public boolean recognizeRawInputCharacter(Context context, SourcecodeInputReader sis) {
		int c = 0;
		try {
			c = sis.read();
		} catch (IOException e) {
			throw new WrappedIOException(e);
		}
		Fragment fragment = new Fragment(context.getFormatProvider());
		fragment.setFragment(""+(char)c);
		fragment.setFormattedFragment(""+(char)c);
		context.push(fragment);
		return true;
	}
}
