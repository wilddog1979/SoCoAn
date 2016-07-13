package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;

public class JavaBaseProcessors extends LangProcessors {

	private static final String ID_JAVADOC = "javadoc";
	
	private static final String ID_LINECOMMENT = "linecomment";
	
	private static final String ID_BLOCKCOMMENT = "blockcomment";
	
	public Fragment processComment(Context subcontext) {
		CommentFragment fragment = new CommentFragment(subcontext.getFormatProvider());
		aggregateContext(fragment, subcontext);
		
		Fragment firstfragment = subcontext.get(0);
		if (ID_JAVADOC.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.JavaDoc);
		} else if (ID_LINECOMMENT.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.LineComment);
		} else if (ID_BLOCKCOMMENT.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.BlockComment);
		}
		
		subcontext.get(1).getFormattedFragment().ifPresent(s -> fragment.setComment(s));
		
		return fragment;
	}
	
	public Fragment processSeparator(Context subcontext) {
		SeparatorFragment fragment = new SeparatorFragment(subcontext.getFormatProvider());
		aggregateContext(fragment, subcontext);
		
		subcontext.stream()
			.filter(subfragment -> subfragment instanceof CommentFragment && ((CommentFragment)subfragment).getType() == CommentFragment.Type.JavaDoc)
			.forEach(subfragment -> fragment.setJavadoc((CommentFragment)subfragment));
		
		return fragment;
	}
	
	public Fragment processSimpleCommand(Context subcontext) {
		Fragment fragment = processAggregation(subcontext);
		
		fragment.getFragment().ifPresent(s -> fragment.setFragment(s+";"));
		fragment.getFormattedFragment().ifPresent(s -> fragment.setFormattedFragment(s+";"));
		
		return fragment;
	}
}
