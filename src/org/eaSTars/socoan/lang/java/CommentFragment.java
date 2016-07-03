package org.eaSTars.socoan.lang.java;

import org.eaSTars.socoan.lang.Fragment;

public class CommentFragment extends Fragment {
	
	public static enum Type {
		JavaDoc,
		LineComment,
		BlockComment
	}
	
	private Type type;

	private String comment;
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public void setFormattedFragment(String formattedFragment) {
	}
	
	@Override
	public String getFormattedFragment() {
		String result = null;
		
		switch (type) {
		case JavaDoc:
			result = String.format(JavaFormat.JAVADOC_FORMAT, getComment());
			break;
		case LineComment:
			result = String.format(JavaFormat.LINECOMMENT_FORMAT, getComment());
			break;
		case BlockComment:
			result = String.format(JavaFormat.BLOCKCOMMENT_FORMAT, getComment());
			break;
		default:
			break;
		}
		
		return result;
	}
}
