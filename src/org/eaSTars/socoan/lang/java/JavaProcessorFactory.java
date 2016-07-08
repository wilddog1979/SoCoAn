package org.eaSTars.socoan.lang.java;

import java.util.HashMap;
import java.util.Map;

import org.eaSTars.socoan.lang.AggregatingProcessor;
import org.eaSTars.socoan.lang.ProcessorFactory;
import org.eaSTars.socoan.lang.SubcontextProcessor;

public class JavaProcessorFactory extends ProcessorFactory {

	public static final String COMMENT_PROCESSOR = "comment";
	
	public static final String SEPARATOR_PROCESSOR = "separator";
	
	public static final String SIMPLECOMMAND_PROCESSOR = "simplecommand";
	
	public static final String AGGREGATING_PROCESSOR = "aggregating";
	
	private static final Map<String, SubcontextProcessor> SUBCONTEXTPROCESSORS =
			new HashMap<String, SubcontextProcessor>() {
				private static final long serialVersionUID = 5365113095278146024L;
			{
				put(COMMENT_PROCESSOR, new CommentProcessor());
				put(SEPARATOR_PROCESSOR, new SeparatorProcessor());
				put(SIMPLECOMMAND_PROCESSOR, new SimpleCommandProcessor());
				put(AGGREGATING_PROCESSOR, new AggregatingProcessor());
			}};
	
	@Override
	public SubcontextProcessor createProcessor(String id) {
		return SUBCONTEXTPROCESSORS.get(id);
	}

}
