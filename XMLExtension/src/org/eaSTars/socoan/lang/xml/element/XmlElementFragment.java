package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.DefaultFragmentImpl;
import org.eaSTars.socoan.lang.FormatProvider;

public abstract class XmlElementFragment extends DefaultFragmentImpl {

	private Comment comment;
	
	public XmlElementFragment(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
