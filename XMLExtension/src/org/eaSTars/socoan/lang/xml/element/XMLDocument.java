package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.FormatProvider;

import java.util.ArrayList;
import java.util.List;

import org.eaSTars.socoan.lang.DefaultFragmentImpl;

public class XMLDocument extends DefaultFragmentImpl {

	private XmlDecl xmlDecl;
	
	private Doctypedecl doctypeDecl;
	
	private List<PI> PIs = new ArrayList<PI>();
	
	public XMLDocument(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public XmlDecl getXmlDecl() {
		return xmlDecl;
	}

	public void setXmlDecl(XmlDecl xmlDecl) {
		this.xmlDecl = xmlDecl;
	}

	public Doctypedecl getDoctypeDecl() {
		return doctypeDecl;
	}

	public void setDoctypeDecl(Doctypedecl doctypeDecl) {
		this.doctypeDecl = doctypeDecl;
	}

	public List<PI> getPIs() {
		return PIs;
	}

}
