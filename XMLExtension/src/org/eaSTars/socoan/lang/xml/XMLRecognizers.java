package org.eaSTars.socoan.lang.xml;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangRecognizers;

public class XMLRecognizers extends LangRecognizers {

	private boolean recognizer(Context context, SourcecodeInputReader sis, String[] samples) {
		StringBuffer sb = new StringBuffer();
		int c = -1;
		Optional<String> match = Optional.empty();
		do {
			try {
				c = sis.read();
			} catch (IOException e) {
				c = -1;
			}
			if (c != -1) {
				sb.append((char)c);
			}
			match = containsAny(sb, samples);
		} while(c != -1 && !match.isPresent());
		
		boolean result = false;
		if (match.isPresent()) {
			String closing = match.get();
			Fragment fragment = new Fragment(context.getFormatProvider());
			fragment.setFragment(sb.substring(0, sb.length() - closing.length()));
			fragment.setFormattedFragment(sb.substring(0, sb.length() - closing.length()));
			context.push(fragment);
			sis.unread(closing);
			result = true;
		} else {
			sis.unread(sb.toString());
		}
		
		return result;
	}
	
	private Optional<String> containsAny(StringBuffer sb, String[] samples) {
		return Arrays.asList(samples).stream().filter(sample -> sb.indexOf(sample) != -1).findFirst();
	}
	
	public boolean recognizePIChars(Context context, SourcecodeInputReader sis) {
		return recognizer(context, sis, new String[]{"?>"});
	}
	
	public boolean recognizeCData(Context context, SourcecodeInputReader sis) {
		return recognizer(context, sis, new String[]{"]]>"});
	}
	
	public boolean recognizeIgnore(Context context, SourcecodeInputReader sis) {
		return recognizer(context, sis, new String[]{"<![", "]]>"});
	}
}
