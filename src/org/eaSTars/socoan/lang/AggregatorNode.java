package org.eaSTars.socoan.lang;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.eaSTars.socoan.lang.base.LiteralFragment;
import org.eaSTars.socoan.lang.java.SeparatorNode;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
	ComplexNode.class,
	SeparatorNode.class
})
public abstract class AggregatorNode extends LanguageNode {

	@Override
	protected LanguageContext processSubcontext(LanguageContext subcontext) {
		
		StringBuffer fragment = new StringBuffer();
		StringBuffer content = new StringBuffer();
		for (LanguageFragment languageFragment : subcontext) {
			fragment.append(notnull(languageFragment.getFragment()));
			content.append(notnull(((LiteralFragment)languageFragment).getContent()));
		}
		
		LiteralFragment literalFragment = new LiteralFragment();
		literalFragment.setFragment(fragment.length() == 0 ? null : fragment.toString());
		literalFragment.setContent(content.length() == 0 ? null : content.toString());
		
		LanguageContext ctx = new LanguageContext(subcontext.getParentContext());
		ctx.push(literalFragment);
		
		return ctx;
	}
	
	private String notnull(String content) {
		return content == null ? "" : content;
	}
}
