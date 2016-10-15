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
import org.eaSTars.socoan.lang.xml.element.GEDecl;
import org.eaSTars.socoan.lang.xml.element.IntSubset;
import org.eaSTars.socoan.lang.xml.element.NotationDecl;
import org.eaSTars.socoan.lang.xml.element.PEDecl;
import org.eaSTars.socoan.lang.xml.element.PI;
import org.eaSTars.socoan.lang.xml.element.STag;
import org.eaSTars.socoan.lang.xml.element.Tag;
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
					fragment.getFragment().ifPresent(xmldecl::setVersionNum);
					break;
				case "EncName":
					fragment.getFragment().ifPresent(xmldecl::setEncodingName);
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
				fragment.getFragment().ifPresent(content::append);
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
					fragment.getFragment().ifPresent(pi::setContent);
					break;
				case "PITarget":
					fragment.getFragment().ifPresent(pi::setTarget);
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
					fragment.getFragment().ifPresent(doctypeDecl::setName);
					break;
				case "ExternalID":
					doctypeDecl.setExternelId((ExtID) fragment);
					break;
				case "Comment":
					comment = (Comment) fragment;
					break;
				case "PEReference":
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
				fragment.getFragment().ifPresent(notationDecl::setName);
			} else if ("ExtID".equals(fragment.getId()) && fragment instanceof ExtID) {
				notationDecl.setExternalId((ExtID) fragment);
			}
		}
		
		return notationDecl;
	}
	
	public STag processEmptyElementTag(Context context) {
		STag emptyElementTag = new STag(context.getFormatProvider());
		aggregateContext(emptyElementTag, context);
		
		String attributename[] = {null};
		for (Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "Name":
					fragment.getFragment().ifPresent(emptyElementTag::setName);
					break;
				case "AttributeName":
					fragment.getFragment().ifPresent(s -> attributename[0] = s);
					break;
				case "AttributeValue":
					if (attributename[0] != null) {
						fragment.getFragment().ifPresent(s -> emptyElementTag.getAttributes().put(attributename[0], s.substring(1, s.length() - 1)));
						attributename[0] = null;
					}
					break;
				}
			}
			
		}
		
		return emptyElementTag;
	}
	
	public STag processSTag(Context context) {
		STag stag = new STag(context.getFormatProvider());
		aggregateContext(stag, context);
		
		String attributename[] = {null};
		for (Fragment fragment : context) {
			if (fragment.getId() != null) {
				switch(fragment.getId()) {
				case "Name":
					fragment.getFragment().ifPresent(stag::setName);
					break;
				case "AttributeName":
					fragment.getFragment().ifPresent(s -> attributename[0] = s);
					break;
				case "AttributeValue":
					if (attributename[0] != null) {
						fragment.getFragment().ifPresent(s -> stag.getAttributes().put(attributename[0], s.substring(1, s.length() - 1)));
						attributename[0] = null;
					}
					break;
				}
			}
			
		}
		
		return stag;
	}
	
	public ETag processETag(Context context) {
		ETag etag = new ETag(context.getFormatProvider());
		aggregateContext(etag, context);
		
		for (Fragment fragment : context) {
			if ("Name".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(etag::setName);
			}
		}
		
		return etag;
	}
	
	public Tag processElement(Context context) {
		Tag tag = new Tag(context.getFormatProvider());
		aggregateContext(tag, context);
		
		Comment comment = null;
		for (Fragment fragment : context) {
			if ("EmptyElementTag".equals(fragment.getId())) {
				tag.setStartingComponent(fragment);
			} else if ("STag".equals(fragment.getId())) {
				tag.setStartingComponent(fragment);
			} else if ("ETag".equals(fragment.getId())) {
				tag.setEndingComponent(fragment);
			} else {
				tag.getComponents().add(fragment);
				if (fragment instanceof Comment) {
					comment = (Comment) fragment;
				} else if (fragment instanceof XmlElementFragment && comment != null) {
					((XmlElementFragment)fragment).setComment(comment);
					comment = null;
				}
			}
		}
		
		return tag;
	}
	
	public CDSect processCDSect(Context context) {
		CDSect cdSect = new CDSect(context.getFormatProvider());
		aggregateContext(cdSect, context);
		
		for(Fragment fragment : context) {
			if ("CData".equals(fragment.getId())) {
				fragment.getFragment().ifPresent(cdSect::setData);
			}
		}
		
		return cdSect;
	}
	
	public XMLDocument processDocument(Context context) {
		XMLDocument xmlDocument = new XMLDocument(context.getFormatProvider());
		
		Comment comment = null;
		for(Fragment fragment : context) {
			xmlDocument.addComponent(fragment);
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
