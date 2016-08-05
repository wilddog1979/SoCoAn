package org.eaSTars.socoan.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eaSTars.socoan.SourcecodeInputStream;

public abstract class ProcessorFactory {

	public static final String AGGREGATING_PROCESSOR = "aggregating";
	public static final String FALLTHROUGH_PROCESSOR = "fallthrough";
	
	private static final Map<String, Function<Context, Fragment>> FUNCTION_MAP =
			new HashMap<String, Function<Context, Fragment>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					LangProcessors processors = new LangProcessors();
					put(AGGREGATING_PROCESSOR, context -> processors.processAggregation(context));
					put(FALLTHROUGH_PROCESSOR, context -> processors.processFallThrought(context));
				}
	};
	
	public Function<Context, Fragment> createProcessor(String id) {
		return FUNCTION_MAP.get(id);
	}
	
	private static final Map<String, RecognizingFunction<Language, Fragment, Boolean>> CHECKERS_MAP =
			new HashMap<String, RecognizingFunction<Language, Fragment, Boolean>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					//LangChecker checkers = new LangChecker();
				}
	};
	
	public RecognizingFunction<Language, Fragment, Boolean> createChecker(String id) {
		return CHECKERS_MAP.get(id);
	}
	
	private static final Map<String , RecognizingFunction<Context, SourcecodeInputStream, Boolean>> RECOGNIZER_MAP =
			new HashMap<String, RecognizingFunction<Context, SourcecodeInputStream, Boolean>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					//LangRecognizers recognizers = new LangRecognizers();
				}
	};
	
	public RecognizingFunction<Context, SourcecodeInputStream, Boolean> createRecognizer(String id) {
		return RECOGNIZER_MAP.get(id);
	}
}
