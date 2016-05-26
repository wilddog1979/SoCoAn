package org.eaSTars;

import org.eaSTars.socoan.Configuration;

public class SoCoAn {

	private static void printHelp() {
		System.out.println("Usage: org.eaSTars.SoCoAn <configuration file>");
	}
	
	public static void main(String[] args) {
		System.out.println("Source Code Analyzer experimental project");
		Configuration configuration = new Configuration();
		if (configuration.processArguments(args)) {
			new SoCoAn(configuration);
		} else {
			printHelp();
		}
	}

	private SoCoAn(Configuration configuration) {
		
	}
}
