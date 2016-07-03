package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.AggregatingComplexType;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;

public class SimpleCommandType extends AggregatingComplexType {
	
	@Override
	public Fragment processSubcontext(Context subcontext) {
		Fragment fragment = super.processSubcontext(subcontext);
		if (fragment.getFragment() != null) {
			fragment.setFragment(fragment.getFragment()+";");
		}
		if (fragment.getFormattedFragment() != null) {
			fragment.setFormattedFragment(fragment.getFormattedFragment()+";");
		}
		return fragment;
	}

}
