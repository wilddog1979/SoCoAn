package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.AggregatingProcessor;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;

public class SimpleCommandProcessor extends AggregatingProcessor {

	@Override
	public Fragment processSubcontext(Context subcontext) {
		Fragment fragment = super.processSubcontext(subcontext);
		
		fragment.getFragment().ifPresent(s -> fragment.setFragment(s+";"));
		fragment.getFormattedFragment().ifPresent(s -> fragment.setFormattedFragment(s+";"));
		
		return fragment;
	}
}
