package org.eaSTars.socoan.lang.java;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.ProcessorFactory;

public class JavaProcessorFactory extends ProcessorFactory {

	public static final String COMMENT_PROCESSOR = "comment";
	
	public static final String SEPARATOR_PROCESSOR = "separator";
	
	public static final String SIMPLECOMMAND_PROCESSOR = "simplecommand";
	
	public static final String AGGREGATING_PROCESSOR = "aggregating";
	
	private static final Map<String, Function<Context, Fragment>> FUNCTION_MAP =
			new HashMap<String, Function<Context, Fragment>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					JavaBaseProcessors processors = new JavaBaseProcessors();
					put(COMMENT_PROCESSOR, context -> processors.processComment(context));
					put(SEPARATOR_PROCESSOR, context -> processors.processSeparator(context));
					put(SIMPLECOMMAND_PROCESSOR, context -> processors.processSimpleCommand(context));
					put(AGGREGATING_PROCESSOR, context -> processors.processAggregation(context));
				}
	};
	
	public Function<Context, Fragment> createProcessor(String id) {
		return FUNCTION_MAP.get(id);
	}

}
