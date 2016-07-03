package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class AggregatingComplexType extends ComplexType {

	@Override
	public Fragment processSubcontext(Context subcontext) {
		Fragment fragment = new Fragment();
		StringBuffer frgment = new StringBuffer();
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < subcontext.size(); ++i) {
			Fragment subfragment = subcontext.get(i);
			if (subfragment.getFragment() != null) {
				frgment.append(subfragment.getFragment());
			}
			if (subfragment.getFormattedFragment() != null) {
				content.append(subfragment.getFormattedFragment());
			}
		}
		if (frgment.length() != 0) {
			fragment.setFragment(frgment.toString());
		}
		if (content.length() != 0) {
			fragment.setFormattedFragment(content.toString());
		}
		return fragment;
	}
}
