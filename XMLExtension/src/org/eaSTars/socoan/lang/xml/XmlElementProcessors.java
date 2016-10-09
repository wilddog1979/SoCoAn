package org.eaSTars.socoan.lang.xml;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.xml.component.ExtID;
import org.eaSTars.socoan.lang.xml.element.AttlistDecl;
import org.eaSTars.socoan.lang.xml.element.CDSect;
import org.eaSTars.socoan.lang.xml.element.Comment;
import org.eaSTars.socoan.lang.xml.element.Doctypedecl;
import org.eaSTars.socoan.lang.xml.element.ETag;
import org.eaSTars.socoan.lang.xml.element.ElementDecl;
import org.eaSTars.socoan.lang.xml.element.EmptyElementTag;
import org.eaSTars.socoan.lang.xml.element.GEDecl;
import org.eaSTars.socoan.lang.xml.element.IntSubset;
import org.eaSTars.socoan.lang.xml.element.NotationDecl;
import org.eaSTars.socoan.lang.xml.element.PEDecl;
import org.eaSTars.socoan.lang.xml.element.PI;
import org.eaSTars.socoan.lang.xml.element.STag;
import org.eaSTars.socoan.lang.xml.element.XMLDocument;
import org.eaSTars.socoan.lang.xml.element.XmlDecl;
import org.eaSTars.socoan.lang.xml.element.XmlElementFragment;

public class XmlElementProcessors extends LangProcessors {

	public XmlDecl processXmlDecl(Context context) {
		XmlDecl xmldecl = new XmlDecl(context.getFormatProvider());
		aggregateContext(xmldecl, context);
		
		for (Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "VersionNum":
					fragment.getFragment().ifPresent(s -> xmldecl.setVersionNum(s));
					break;
				case "EncName":
					fragment.getFragment().ifPresent(s -> xmldecl.setEncodingName(s));
					break;
				case "SDFlag":
					fragment.getFragment().ifPresent(s -> xmldecl.setStandalone("yes".equalsIgnoreCase(s)));
					break;
				}
			}
		}
		
		return xmldecl;
	}
	
	public Comment processComment(Context context) {
		Comment comment = new Comment(context.getFormatProvider());
		aggregateContext(comment, context);
		
		StringBuffer content = new StringBuffer();
		for (Fragment fragment : context) {
			if (!"CommentStart".equals(fragment.getId()) && !"CommentEnd".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(s -> content.append(s));
			}
		}
		if (content.length() != 0) {
			comment.setComment(content.toString());
		}
		
		return comment;
	}
	
	public PI processPI(Context context) {
		PI pi = new PI(context.getFormatProvider());
		aggregateContext(pi, context);
		
		for (Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "CharWithoutPIEnd":
					fragment.getFragment().ifPresent(s -> pi.setContent(s));
					break;
				case "PITarget":
					fragment.getFragment().ifPresent(s -> pi.setTarget(s));
					break;
				}
			}
		}
		
		return pi;
	}
	
	public Doctypedecl processDoctypedecl(Context context) {
		Doctypedecl doctypeDecl = new Doctypedecl(context.getFormatProvider());
		aggregateContext(doctypeDecl, context);
		
		Comment comment = null;
		for(Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "Name":
					fragment.getFragment().ifPresent(s -> doctypeDecl.setName(s));
					break;
				case "ExternalID":
					doctypeDecl.setExternelId((ExtID) fragment);
					break;
				case "Comment":
					comment = (Comment) fragment;
					break;
					//TODO add PEReference also - component declaration is required
				case "elementdecl":
				case "AttlistDecl":
				case "GEDecl":
				case "PEDecl":
				case "NotationDecl":
				case "PI":
					((XmlElementFragment)fragment).setComment(comment);
					comment = null;
					doctypeDecl.getIntSubsets().add((IntSubset) fragment);
					break;
				}
			}
		}
		
		return doctypeDecl;
	}
	
	public ElementDecl processElementDecl(Context context) {
		ElementDecl elementDecl = new ElementDecl(context.getFormatProvider());
		aggregateContext(elementDecl, context);
		return elementDecl;
	}
	
	public AttlistDecl processAttlistDecl(Context context) {
		AttlistDecl attlistDecl = new AttlistDecl(context.getFormatProvider());
		aggregateContext(attlistDecl, context);
		return attlistDecl;
	}
	
	public GEDecl processGEDecl(Context context) {
		GEDecl geDecl = new GEDecl(context.getFormatProvider());
		aggregateContext(geDecl, context);
		return geDecl;
	}
	
	public PEDecl processPEDecl(Context context) {
		PEDecl peDecl = new PEDecl(context.getFormatProvider());
		aggregateContext(peDecl, context);
		return peDecl;
	}
	
	public NotationDecl processNotationDecl(Context context) {
		NotationDecl notationDecl = new NotationDecl(context.getFormatProvider());
		aggregateContext(notationDecl, context);
		
		for(Fragment fragment : context) {
			if ("Name".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(s -> notationDecl.setName(s));
			} else if ("ExtID".equals(fragment.getId()) && fragment instanceof ExtID) {
				notationDecl.setExternalId((ExtID) fragment);
			}
		}
		
		return notationDecl;
	}
	
	public EmptyElementTag processEmptyElementTag(Context context) {
		EmptyElementTag emptyElementTag = new EmptyElementTag(context.getFormatProvider());
		aggregateContext(emptyElementTag, context);
		return emptyElementTag;
	}
	
	public STag processSTag(Context context) {
		STag stag = new STag(context.getFormatProvider());
		aggregateContext(stag, context);
		return stag;
	}
	
	public ETag processETag(Context context) {
		ETag etag = new ETag(context.getFormatProvider());
		aggregateContext(etag, context);
		return etag;
	}
	
	public CDSect processCDSect(Context context) {
		CDSect cdSect = new CDSect(context.getFormatProvider());
		aggregateContext(cdSect, context);
		return cdSect;
	}
	
	public XMLDocument processDocument(Context context) {
		XMLDocument xmlDocument = new XMLDocument(context.getFormatProvider());
		aggregateContext(xmlDocument, context);
		
		Comment comment = null;
		for(Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "Comment":
					comment = (Comment) fragment;
					break;
				case "XMLDecl":
					xmlDocument.setXmlDecl((XmlDecl) fragment);
					break;
				case "doctypedecl":
					Doctypedecl doctypeDecl = (Doctypedecl) fragment;
					doctypeDecl.setComment(comment);
					comment = null;
					xmlDocument.setDoctypeDecl(doctypeDecl);
					break;
				case "PI":
					PI pi = (PI) fragment;
					pi.setComment(comment);
					comment = null;
					xmlDocument.getPIs().add(pi);
					break;
				}
			}
		}
		
		return xmlDocument;
	}
}
