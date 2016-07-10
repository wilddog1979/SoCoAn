package org.eaSTars.socoan.lang.test;

import java.util.function.Function;

import org.eaSTars.socoan.lang.ComplexType;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ProcessorFactory;

public class ComplexTypeHelper extends ComplexType {

	private class HelperLanguage extends Language {
		public void setProcessFactory(ProcessorFactory processFactory) {
			this.processorFactory = processFactory;
		}
	}
	
	private class HelperProcessorFactory extends ProcessorFactory {

		private Function<Context, Fragment> function;
		
		@Override
		public Function<Context, Fragment> createProcessor(String id) {
			return function;
		}
		
	}
	
	public ComplexTypeHelper(Function<Context, Fragment> function) {
		HelperLanguage language = new HelperLanguage();
		HelperProcessorFactory processFactory = new HelperProcessorFactory();
		processFactory.function = function;
		language.setProcessFactory(processFactory);
		this.parent = language;
	}
}
