package org.eaSTars.socoan.lang;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FormatEntriesAdapter extends XmlAdapter<FormatEntryType, Map<String, String>> {

	@Override
	public Map<String, String> unmarshal(FormatEntryType v) throws Exception {
		Map<String, String> entries = new HashMap<String, String>();
		v.entryList.forEach(element -> entries.put(element.getKey(), element.getValue()));
		return entries;
	}

	@Override
	public FormatEntryType marshal(Map<String, String> v) throws Exception {
		FormatEntryType entries = new FormatEntryType();
		v.entrySet().forEach(entry -> entries.entryList.add(new FormatEntry(entry.getKey(), entry.getValue())));
		return entries;
	}

}
