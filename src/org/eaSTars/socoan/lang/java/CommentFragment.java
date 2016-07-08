package org.eaSTars.socoan.lang.java;

import java.util.Optional;

import org.eaSTars.socoan.lang.Fragment;

public class CommentFragment extends Fragment {
	
	public static enum Type {
		JavaDoc,
		LineComment,
		BlockComment
	}
	
	private Type type;

	private Optional<String> comment;
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Optional<String> getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = Optional.ofNullable(comment);
	}
	
	@Override
	public void setFormattedFragment(String formattedFragment) {
	}
	
	@Override
	public Optional<String> getFormattedFragment() {
		Optional<String> result = Optional.empty();
		
		switch (type) {
		case JavaDoc:
			result = getComment().map(s -> String.format(JavaFormat.JAVADOC_FORMAT, s));
			break;
		case LineComment:
			result = getComment().map(s -> String.format(JavaFormat.LINECOMMENT_FORMAT, s));
			break;
		case BlockComment:
			result = getComment().map(s -> String.format(JavaFormat.BLOCKCOMMENT_FORMAT, s));
			break;
		default:
			break;
		}
		
		return result;
	}
}
