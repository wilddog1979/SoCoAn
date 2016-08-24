package org.eaSTars.socoan.lang.java.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.java.CommentFragment;
import org.eaSTars.socoan.lang.test.AbstractLangTest;

public abstract class AbstractJavaLangTest extends AbstractLangTest {

	protected boolean recognizetype(AbstractTypeDeclaration typeDeclaration, Context context, SourcecodeInputStream sis) {
		boolean testresult = false;
		try {
			testresult = typeDeclaration.recognizeType(context, sis);
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
		return testresult;
	}
	
	protected void checkLeftover(SourcecodeInputStream sis, int leftover) {
		try {
			if (leftover == 0) {
				assertEquals("The input stream should not contain any leftover characters", leftover, sis.available());
			} else {
				assertEquals("The input stream should contain the leftover characters", leftover, sis.available());
			}
		} catch (IOException e) {
			fail("Unexpected exception occured: "+e.getMessage());
		}
	}
	
	protected void testCommentFragment(CommentFragment fragment, CommentFragment.Type type, String fragmentcontent, String formattedcontent, String commentcontent) {
		assertEquals("Comment element should contain the expected type", type, fragment.getType());
		testFragment(fragment, fragmentcontent, formattedcontent);
		testOptionalString("Commentfragment", commentcontent, fragment.getComment());
	}
}
