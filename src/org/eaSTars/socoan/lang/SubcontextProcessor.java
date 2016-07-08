package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SubcontextProcessor {

	public abstract Fragment processSubcontext(Context subcontext);

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
}
