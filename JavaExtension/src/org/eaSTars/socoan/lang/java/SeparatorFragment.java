package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.Fragment;

public class SeparatorFragment extends Fragment {

	private CommentFragment javadoc;

	public SeparatorFragment(FormatProvider formatProvider) {
		super(formatProvider);
	}
	
	public CommentFragment getJavadoc() {
		return javadoc;
	}

	public void setJavadoc(CommentFragment javadoc) {
		this.javadoc = javadoc;
	}
	
}
