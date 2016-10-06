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

	private static final String XMLDECL_PROCESSOR = "XmlDeclProcessor";
	private static final String COMMENT_PROCESSOR = "CommentProcessor";
	private static final String PI_PROCESSOR = "PIProcessor";
	private static final String PROLOG_PROCESSOR = "prologProcessor";
	private static final String ELEMENTDECL_PROCESSOR = "ElementDeclProcessor";
	private static final String ATTLISTDECL_PROCESSOR = "AttlistDeclProcessor";
	private static final String GEDECL_PROCESSOR = "GEDeclProcessor";
	private static final String PEDECL_PROCESSOR = "PEDeclProcessor";
	private static final String NOTATIONDECL_PROCESSOR = "NotationDeclProcessor";
	private static final String EMPTYELEMENTTAG_PROCESSOR = "EmptyElementTagProcessor";
	private static final String STAG_PROCESSOR = "STagProcessor";
	private static final String ETAG_PROCESSOR = "ETagProcessor";
	private static final String CDSECT_PROCESSOR = "CDSectProcessor";
	private static final String DOCUMENT_PROCESSOR = "DocumentProcessor";
	
	private static final Map<String, Function<Context, Fragment>> FUNCTION_MAP =
			new HashMap<String, Function<Context, Fragment>>() {
				private static final long serialVersionUID = -7867236509077047915L;
		{
			XmlBaseProcessors processors = new XmlBaseProcessors();
			put(XMLDECL_PROCESSOR, (context) -> processors.processXmlDecl(context));
			put(COMMENT_PROCESSOR, (context) -> processors.processComment(context));
			put(PI_PROCESSOR, (context) -> processors.processPI(context));
			put(PROLOG_PROCESSOR, (context) -> processors.processProlog(context));
			put(ELEMENTDECL_PROCESSOR, (context) -> processors.processElementDecl(context));
			put(ATTLISTDECL_PROCESSOR, (context) -> processors.processAttlistDecl(context));
			put(GEDECL_PROCESSOR, (context) -> processors.processGEDecl(context));
			put(PEDECL_PROCESSOR, (context) -> processors.processPEDecl(context));
			put(NOTATIONDECL_PROCESSOR, (context) -> processors.processNotationDecl(context));
			put(EMPTYELEMENTTAG_PROCESSOR, (context) -> processors.processEmptyElementTag(context));
			put(STAG_PROCESSOR, (context) -> processors.processSTag(context));
			put(ETAG_PROCESSOR, (context) -> processors.processETag(context));
			put(CDSECT_PROCESSOR, (context) -> processors.processCDSect(context));
			put(DOCUMENT_PROCESSOR, (context) -> processors.processDocument(context));
		}
	};
	
	@Override
	public Function<Context, Fragment> createProcessor(String id) {
		Function<Context, Fragment> result = FUNCTION_MAP.get(id);
		return result == null ? super.createProcessor(id) : result;
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
