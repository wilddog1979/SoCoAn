package org.eaSTars.socoan.lang.java;

import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.ComplexType;
import org.eaSTars.socoan.lang.ComplexTypeNode;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;

public class SeparatorType extends ComplexType {

	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException {
		Context subcontext = new Context(context);
		
		boolean result = false;
		do {
			for (ComplexTypeNode startnode : getStartnodes()) {
				result = recognizeNode(startnode, subcontext, sis);
				if (result) {
					break;
				}
			}
		} while (result);
		
		if (subcontext.size() != 0) {
			Fragment fragment = processSubcontext(subcontext);
			fragment.setId(this.getId());
			context.push(fragment);
		}
		
		return subcontext.size() != 0;
	}
	
	@Override
	public Fragment processSubcontext(Context subcontext) {
		SeparatorFragment fragment = new SeparatorFragment();
		
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
			if (subfragment instanceof CommentFragment && ((CommentFragment)subfragment).getType() == CommentFragment.Type.JavaDoc) {
				fragment.setJavadoc((CommentFragment)subfragment);
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
