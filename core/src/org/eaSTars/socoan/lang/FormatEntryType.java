package org.eaSTars.socoan.lang;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class FormatEntryType {

	@XmlElement(name="entry")
	public List<FormatEntry> entryList = new ArrayList<FormatEntry>();
}
