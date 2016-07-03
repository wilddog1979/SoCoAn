package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.ComplexType;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;

public class CommentType extends ComplexType {

	private static final String ID_JAVADOC = "javadoc";
	
	private static final String ID_LINECOMMENT = "linecomment";
	
	private static final String ID_BLOCKCOMMENT = "blockcomment";
	
	@Override
	public Fragment processSubcontext(Context subcontext) {
		CommentFragment fragment = new CommentFragment();
		
		Fragment firstfragment = subcontext.get(0);
		Fragment secondfragment = subcontext.get(1);
		if (ID_JAVADOC.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.JavaDoc);
		} else if (ID_LINECOMMENT.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.LineComment);
		} else if (ID_BLOCKCOMMENT.equals(firstfragment.getId())) {
			fragment.setType(CommentFragment.Type.BlockComment);
		}
		
		fragment.setComment(secondfragment.getFormattedFragment());
		fragment.setFragment(firstfragment.getFragment() + secondfragment.getFragment());
		
		return fragment;
	}

}
