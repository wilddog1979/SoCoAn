package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.SubcontextProcessor;

public class SeparatorProcessor extends SubcontextProcessor {

	@Override
	public Fragment processSubcontext(Context subcontext) {
		SeparatorFragment fragment = new SeparatorFragment();
		aggregateContext(fragment, subcontext);
		
		subcontext.stream()
			.filter(subfragment -> subfragment instanceof CommentFragment && ((CommentFragment)subfragment).getType() == CommentFragment.Type.JavaDoc)
			.forEach(subfragment -> fragment.setJavadoc((CommentFragment)subfragment));
		
		return fragment;
	}

}
