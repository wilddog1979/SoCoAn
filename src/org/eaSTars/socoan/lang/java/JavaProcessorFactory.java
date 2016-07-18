package org.eaSTars.socoan.lang.java;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eaSTars.socoan.SourcecodeInputStream;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ProcessorFactory;
import org.eaSTars.socoan.lang.RecognizingFunction;

public class JavaProcessorFactory extends ProcessorFactory {

	public static final String COMMENT_PROCESSOR = "comment";
	
	public static final String SEPARATOR_PROCESSOR = "separator";
	
	public static final String SIMPLECOMMAND_PROCESSOR = "simplecommand";
	
	private static final Map<String, Function<Context, Fragment>> FUNCTION_MAP =
			new HashMap<String, Function<Context, Fragment>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					JavaBaseProcessors processors = new JavaBaseProcessors();
					put(COMMENT_PROCESSOR, context -> processors.processComment(context));
					put(SEPARATOR_PROCESSOR, context -> processors.processSeparator(context));
					put(SIMPLECOMMAND_PROCESSOR, context -> processors.processSimpleCommand(context));
				}
	};
	
	public Function<Context, Fragment> createProcessor(String id) {
		Function<Context, Fragment> result = FUNCTION_MAP.get(id);
		return result == null ? super.createProcessor(id) : result;
	}

	private static final String IDENTIFIER_CHECKER = "IdentifierCheck";
	
	private static final Map<String, RecognizingFunction<Language, Fragment, Boolean>> CHECKERS_MAP =
			new HashMap<String, RecognizingFunction<Language, Fragment, Boolean>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					JavaBaseCheckers checkers = new JavaBaseCheckers();
					put(IDENTIFIER_CHECKER, (language, fragment) -> checkers.checkIdentifierChars(language, fragment));
				}
	};
	
	@Override
	public RecognizingFunction<Language, Fragment, Boolean> createChecker(String id) {
		RecognizingFunction<Language, Fragment, Boolean> result = CHECKERS_MAP.get(id);
		return result == null ? super.createChecker(id) : result;
	}
	
	private static final String JAVALETTER_RECOGNIZER = "JavaLetter";
	
	private static final String JAVALETTERORDIGIT_RECOGNIZER = "JavaLetterOrDigit";
	
	private static final Map<String , RecognizingFunction<Context, SourcecodeInputStream, Boolean>> RECOGNIZER_MAP =
			new HashMap<String, RecognizingFunction<Context, SourcecodeInputStream, Boolean>>() {
				private static final long serialVersionUID = 5082635489425605921L;
				{
					JavaBaseRecognizers recognizers = new JavaBaseRecognizers();
					put(JAVALETTER_RECOGNIZER, (context, sis) -> recognizers.recognizeJavaLetter(context, sis));
					put(JAVALETTERORDIGIT_RECOGNIZER, (context, sis) -> recognizers.recognizeJavaLetterOrDigit(context, sis));
				}
	};
	
	@Override
	public RecognizingFunction<Context, SourcecodeInputStream, Boolean> createRecognizer(String id) {
		RecognizingFunction<Context, SourcecodeInputStream, Boolean> result = RECOGNIZER_MAP.get(id);
		return result == null ? super.createRecognizer(id) : result;
	}
}
