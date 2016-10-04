package org.eaSTars.socoan.lang.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eaSTars.socoan.SourcecodeInputReader;
import org.eaSTars.socoan.lang.Context;
import org.eaSTars.socoan.lang.Fragment;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ProcessorFactory;
import org.eaSTars.socoan.lang.RecognizingFunction;

public class XMLProcessorFactory extends ProcessorFactory {

	@Override
	public Function<Context, Fragment> createProcessor(String id) {
		return super.createProcessor(id);
	}
	
	private static final String CHAR_CHECKER = "CharChecker";
	private static final String NAMESTARTCHAR_CHECKER = "NameStartCharChecker";
	private static final String NAMECHAR_CHECKER = "NameCharChecker";
	private static final String NOTPERCENTANDSINGLEQUOTE_CHECKER = "NotPercentAndSingleQuoteChecker";
	private static final String NOTPERCENTANDDOUBLEQUOTE_CHECKER = "NotPercentAndDoubleQuoteChecker";
	private static final String NOTLOWERANDDOUBLEQUOTE_CHECKER = "NotLowerAndDoubleQuoteChecker";
	private static final String NOTLOWERANDSINGLEQUOTE_CHECKER = "NotLowerAndSingleQuoteChecker";
	private static final String NOTDOUBLEQUOTE_CHECKER = "NotDoubleQuoteChecker";
	private static final String NOTSINGLEQUOTE_CHECKER = "NotSingleQuoteChecker";
	private static final String PUBIDCHAR_CHECKER = "PubidCharChecker";
	private static final String NOTLOWERAND_CHECKER = "NotLowerAndChecker";
	private static final String CHARDATA_CHECKER = "CharDataChecker";
	private static final String CHARWITHOUTDASH_CHECKER = "CharWithoutDashChecker";
	private static final String PITARGET_CHECKER = "PITargetChecker";
	private static final String CHARWITHOUTPIEND_CHECKER = "CharWithoutPIEndChecker";
	private static final String ENCNAMEFIRSTCHAR_CHECKER = "EncNameFirstCharChecker";
	private static final String ENCNAMECHAR_CHECKER = "EncNameCharChecker";
	
	private static final Map<String, RecognizingFunction<Language, Fragment, Boolean>> CHECKERS_MAP =
			new HashMap<String, RecognizingFunction<Language, Fragment, Boolean>>() {
				private static final long serialVersionUID = -7867236509077047915L;
				{
					XMLCheckers checkers = new XMLCheckers();
					put(CHAR_CHECKER, (language, fragment) -> checkers.checkChar(language, fragment));
					put(NAMESTARTCHAR_CHECKER, (language, fragment) -> checkers.checkNameStartChar(language, fragment));
					put(NAMECHAR_CHECKER, (language, fragment) ->checkers.checkNameChar(language, fragment));
					put(NOTPERCENTANDSINGLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotPercentAndSingleQuote(language, fragment));
					put(NOTPERCENTANDDOUBLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotPercentAndDoubleQuote(language, fragment));
					put(NOTLOWERANDSINGLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotLowerAndSingleQuote(language, fragment));
					put(NOTLOWERANDDOUBLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotLowerAndDoubleQuote(language, fragment));
					put(NOTSINGLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotSingleQuote(language, fragment));
					put(NOTDOUBLEQUOTE_CHECKER, (language, fragment) -> checkers.checkNotDoubleQuote(language, fragment));
					put(PUBIDCHAR_CHECKER, (language, fragment) -> checkers.checkPubidChar(language, fragment));
					put(NOTLOWERAND_CHECKER, (language, fragment) -> checkers.checkNotLowerAnd(language, fragment));
					put(CHARDATA_CHECKER, (language, fragment) -> checkers.checkCharData(language, fragment));
					put(CHARWITHOUTDASH_CHECKER, (language, fragment) -> checkers.checkCharWithoutDash(language, fragment));
					put(PITARGET_CHECKER, (language, fragment) -> checkers.checkPITarget(language, fragment));
					put(CHARWITHOUTPIEND_CHECKER, (language, fragment) -> checkers.checkCharWithoutPIEnd(language, fragment));
					put(ENCNAMEFIRSTCHAR_CHECKER, (language, fragment) -> checkers.checkEncNameFirstChar(language, fragment));
					put(ENCNAMECHAR_CHECKER, (language, fragment) -> checkers.checkEncNameChar(language, fragment));
				}
	};
	
	@Override
	public RecognizingFunction<Language, Fragment, Boolean> createChecker(String id) {
		RecognizingFunction<Language, Fragment, Boolean> result = CHECKERS_MAP.get(id);
		return result == null ? super.createChecker(id) : result;
	}
	
	private static final String PICHARS_RECOGNIZER = "PICharsRecognizer";
	private static final String CDATA_RECOGNIZER = "CDataRecognizer";
	private static final String IGNORE_RECOGNIZER = "IgnoreRecognizer";
	
	private static final Map<String , RecognizingFunction<Context, SourcecodeInputReader, Boolean>> RECOGNIZER_MAP =
			new HashMap<String, RecognizingFunction<Context, SourcecodeInputReader, Boolean>>() {
				private static final long serialVersionUID = 477409754677152662L;
				{
					XMLRecognizers recognizers = new XMLRecognizers();
					put(PICHARS_RECOGNIZER, (context, sis) -> recognizers.recognizePIChars(context, sis));
					put(CDATA_RECOGNIZER, (context, sis) -> recognizers.recognizeCData(context, sis));
					put(IGNORE_RECOGNIZER, (context, sis) -> recognizers.recognizeIgnore(context, sis));
				}
	};
	
	@Override
	public RecognizingFunction<Context, SourcecodeInputReader, Boolean> createRecognizer(String id) {
		RecognizingFunction<Context, SourcecodeInputReader, Boolean> result = RECOGNIZER_MAP.get(id);
		return result == null ? super.createRecognizer(id) : result;
	}
}
