package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.assertEquals;

import org.eaSTars.socoan.lang.java.CommentFragment;
import org.eaSTars.socoan.lang.test.AbstractLangTest;

public abstract class AbstractJavaLangTest extends AbstractLangTest {

	protected void testCommentFragment(CommentFragment fragment, CommentFragment.Type type, String fragmentcontent, String formattedcontent, String commentcontent) {
		assertEquals("Comment element should contain the expected type", type, fragment.getType());
		testFragment(fragment, fragmentcontent, formattedcontent);
		testOptionalString("Commentfragment", commentcontent, fragment.getComment());
	}
}
