package org.eaSTars.socoan.lang.xml;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.xml.component.ExtID;
import org.eaSTars.socoan.lang.xml.component.ExtID.Type;
import org.eaSTars.socoan.lang.xml.component.PEReference;
import org.eaSTars.socoan.lang.xml.component.Value;

public class XmlComponentProcessors extends LangProcessors {

	public Value processContentValue(Context context) {
		Value value = new Value(context.getFormatProvider());
		aggregateContext(value, context);
		
		StringBuffer content = new StringBuffer();
		for(Fragment fragment : context) {
			if ("Content".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(content::append);
			}
		}
		if (content.length() != 0) {
			value.setContent(content.toString());
		}
		
		return value;
	}
	
	public ExtID processExternalID(Context context) {
		ExtID externalID = new ExtID(context.getFormatProvider());
		aggregateContext(externalID, context);
		
		for(Fragment fragment : context) {
			if ("ExternalIDType".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(s ->
					externalID.setType("SYSTEM".equals(s) ? Type.System : ("PUBLIC".equals(s) ? Type.Public : null)));
			} else if ("SystemLiteral".equals(fragment.getId()) && fragment instanceof Value) {
				externalID.setSystemLiteral(((Value)fragment).getContent());
			} else if (externalID.getType() == Type.Public && "PubidLiteral".equals(fragment.getId()) && fragment instanceof Value) {
				externalID.setPubidLiteral(((Value)fragment).getContent());
			}
		}
		
		return externalID;
	}
	
	public PEReference processPEReference(Context context) {
		PEReference peReference = new PEReference(context.getFormatProvider());
		aggregateContext(peReference, context);
		
		for(Fragment fragment : context) {
			if ("Name".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(peReference::setName);
			}
		}
		
		return peReference;
	}
}
