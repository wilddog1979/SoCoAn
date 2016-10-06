package org.eaSTars.socoan.lang.xml;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.LangProcessors;
import org.eaSTars.socoan.lang.xml.element.AttlistDecl;
import org.eaSTars.socoan.lang.xml.element.CDSect;
import org.eaSTars.socoan.lang.xml.element.Comment;
import org.eaSTars.socoan.lang.xml.element.ETag;
import org.eaSTars.socoan.lang.xml.element.ElementDecl;
import org.eaSTars.socoan.lang.xml.element.EmptyElementTag;
import org.eaSTars.socoan.lang.xml.element.GEDecl;
import org.eaSTars.socoan.lang.xml.element.NotationDecl;
import org.eaSTars.socoan.lang.xml.element.PEDecl;
import org.eaSTars.socoan.lang.xml.element.PI;
import org.eaSTars.socoan.lang.xml.element.Prolog;
import org.eaSTars.socoan.lang.xml.element.STag;
import org.eaSTars.socoan.lang.xml.element.XMLDocument;
import org.eaSTars.socoan.lang.xml.element.XmlDecl;

public class XmlBaseProcessors extends LangProcessors {

	public XmlDecl processXmlDecl(Context context) {
		XmlDecl xmldecl = new XmlDecl(context.getFormatProvider());
		aggregateContext(xmldecl, context);
		return xmldecl;
	}
	
	public Comment processComment(Context context) {
		Comment comment = new Comment(context.getFormatProvider());
		aggregateContext(comment, context);
		return comment;
	}
	
	public PI processPI(Context context) {
		PI pi = new PI(context.getFormatProvider());
		aggregateContext(pi, context);
		return pi;
	}
	
	public Prolog processProlog(Context context) {
		Prolog prolog = new Prolog(context.getFormatProvider());
		aggregateContext(prolog, context);
		return prolog;
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
		return xmlDocument;
	}
}
