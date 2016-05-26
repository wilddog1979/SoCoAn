package org.eaSTars.socoan;

public class Configuration {

	public boolean processArguments(String[] args) {
		boolean result = true;
		
		int i = 0;
		for (;i < args.length; ++i) {
			if (!args[i].startsWith("-")) {
				break;
			}
			
		}
		if (result) {
			result = i == args.length - 1;
		}
		
		return result;
	}
}
