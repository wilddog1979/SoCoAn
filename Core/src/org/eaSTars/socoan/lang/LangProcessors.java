package org.eaSTars.socoan.lang;

public class LangProcessors {

	protected void aggregateContext(Fragment fragment, Context subcontext) {
		StringBuffer frgment = new StringBuffer();
		StringBuffer content = new StringBuffer();
		
		subcontext.forEach(subfragment -> {
			subfragment.getFragment().ifPresent(s -> frgment.append(s));
			subfragment.getFormattedFragment().ifPresent(s -> content.append(s));
		});
		
		if (frgment.length() != 0) {
			fragment.setFragment(frgment.toString());
		}
		if (content.length() != 0) {
			fragment.setFormattedFragment(content.toString());
		}
	}
	
	public Fragment processAggregation(Context subcontext) {
		DefaultFragmentImpl fragment = new DefaultFragmentImpl(subcontext.getFormatProvider());
		aggregateContext(fragment, subcontext);
		
		return fragment;
	}
	
	public Fragment processFallThrought(Context subcontext) {
		Context parent = subcontext.getParent();
		if (parent != null) {
			subcontext.forEach(fragment -> parent.push(fragment));
		}
		
		return null;
	}
}
