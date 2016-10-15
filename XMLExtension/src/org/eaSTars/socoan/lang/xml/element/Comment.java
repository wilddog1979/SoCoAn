package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.DefaultFragmentImpl;

public class Comment extends DefaultFragmentImpl implements Content {

	private String comment = "";
	
	public Comment(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
