package org.eaSTars.socoan.lang;

public class AggregatingProcessor extends SubcontextProcessor {

	@Override
	public Fragment processSubcontext(Context subcontext) {
		Fragment fragment = new Fragment();
		aggregateContext(fragment, subcontext);
		
		return fragment;
	}

}
