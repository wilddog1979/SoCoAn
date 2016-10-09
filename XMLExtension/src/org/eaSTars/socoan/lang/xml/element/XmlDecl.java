package org.eaSTars.socoan.lang.xml.element;

import org.eaSTars.socoan.lang.DefaultFragmentImpl;
import org.eaSTars.socoan.lang.FormatProvider;

public class XmlDecl extends DefaultFragmentImpl {

	private String versionNum;
	
	private String encodingName;
	
	private boolean standalone;
	
	public XmlDecl(FormatProvider formatProvider) {
		super(formatProvider);
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getEncodingName() {
		return encodingName;
	}

	public void setEncodingName(String encodingName) {
		this.encodingName = encodingName;
	}

	public boolean isStandalone() {
		return standalone;
	}

	public void setStandalone(boolean standalone) {
		this.standalone = standalone;
	}

}
