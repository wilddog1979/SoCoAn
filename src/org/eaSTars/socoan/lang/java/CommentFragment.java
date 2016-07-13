package org.eaSTars.socoan.lang.java;

import java.util.Optional;

import org.eaSTars.socoan.lang.FormatProvider;
import org.eaSTars.socoan.lang.Fragment;

public class CommentFragment extends Fragment {

	private static final String JAVADOC_FORMAT_KEY = "JAVADOC_FORMAT";
	
	private static final String DEFAULT_JAVADOC_FORMAT_VALUE = "<span class=\"javadoc\">/**%s*/</span>";
	
	private static final String LINECOMMENT_FORMAT_KEY = "LINECOMMENT_FORMAT";
	
	private static final String DEFAULT_LINECOMMENT_FORMAT_VALUE = "<span class=\"linecomment\">//%s\n</span>";
	
	private static final String BLOCKCOMMENT_FORMAT_KEY = "BLOCKCOMMENT_FORMAT";
	
	private static final String DEFAULT_BLOCKCOMMENT_FORMAT_VALUE = "<span class=\"blockcomment\">/*%s*/</span>";
	
	public static enum Type {
		JavaDoc,
		LineComment,
		BlockComment
	}
	
	private Type type;

	private Optional<String> comment;
	
	public CommentFragment(FormatProvider formatProvider) {
		super(formatProvider);
	}
	
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
			result = getComment().map(s -> String.format(formatProvider.getFormat(JAVADOC_FORMAT_KEY,DEFAULT_JAVADOC_FORMAT_VALUE), s));
			break;
		case LineComment:
			result = getComment().map(s -> String.format(formatProvider.getFormat(LINECOMMENT_FORMAT_KEY,DEFAULT_LINECOMMENT_FORMAT_VALUE), s));
			break;
		case BlockComment:
			result = getComment().map(s -> String.format(formatProvider.getFormat(BLOCKCOMMENT_FORMAT_KEY,DEFAULT_BLOCKCOMMENT_FORMAT_VALUE), s));
			break;
		default:
			break;
		}
		
		return result;
	}
}
