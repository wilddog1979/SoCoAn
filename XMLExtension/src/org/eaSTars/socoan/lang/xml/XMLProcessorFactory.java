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
	private static final String DOCTYPEDECL_PROCESSOR = "doctypedeclProcessor";
	private static final String ELEMENTDECL_PROCESSOR = "ElementDeclProcessor";
	private static final String ATTLISTDECL_PROCESSOR = "AttlistDeclProcessor";
	private static final String GEDECL_PROCESSOR = "GEDeclProcessor";
	private static final String PEDECL_PROCESSOR = "PEDeclProcessor";
	private static final String NOTATIONDECL_PROCESSOR = "NotationDeclProcessor";
	private static final String EMPTYELEMENTTAG_PROCESSOR = "EmptyElementTagProcessor";
	private static final String STAG_PROCESSOR = "STagProcessor";
	private static final String ETAG_PROCESSOR = "ETagProcessor";
	private static final String ELEMENT_PROCESSOR = "elementProcessor";
	private static final String CDSECT_PROCESSOR = "CDSectProcessor";
	private static final String DOCUMENT_PROCESSOR = "DocumentProcessor";
	
	private static final String CONTENTVALUE_PROCESSOR = "ContentValueProcessor";
	private static final String EXTERNALID_PROCESSOR = "ExternalIDProcessor";
	private static final String PEREFERENCE_PROCESSOR = "PEReferenceProcessor";
	
	private static final Map<String, Function<Context, Fragment>> FUNCTION_MAP =
			new HashMap<String, Function<Context, Fragment>>() {
				private static final long serialVersionUID = -7867236509077047915L;
		{
			XmlElementProcessors elementProcessors = new XmlElementProcessors();
			
			put(XMLDECL_PROCESSOR, elementProcessors::processXmlDecl);
			put(COMMENT_PROCESSOR, elementProcessors::processComment);
			put(PI_PROCESSOR, elementProcessors::processPI);
			put(DOCTYPEDECL_PROCESSOR, elementProcessors::processDoctypedecl);
			put(ELEMENTDECL_PROCESSOR, elementProcessors::processElementDecl);
			put(ATTLISTDECL_PROCESSOR, elementProcessors::processAttlistDecl);
			put(GEDECL_PROCESSOR, elementProcessors::processGEDecl);
			put(PEDECL_PROCESSOR, elementProcessors::processPEDecl);
			put(NOTATIONDECL_PROCESSOR, elementProcessors::processNotationDecl);
			put(EMPTYELEMENTTAG_PROCESSOR, elementProcessors::processEmptyElementTag);
			put(STAG_PROCESSOR, elementProcessors::processSTag);
			put(ETAG_PROCESSOR, elementProcessors::processETag);
			put(ELEMENT_PROCESSOR, elementProcessors::processElement);
			put(CDSECT_PROCESSOR, elementProcessors::processCDSect);
			put(DOCUMENT_PROCESSOR, elementProcessors::processDocument);
			
			XmlComponentProcessors componentProcessors = new XmlComponentProcessors();
			
			put(CONTENTVALUE_PROCESSOR, componentProcessors::processContentValue);
			put(EXTERNALID_PROCESSOR, componentProcessors::processExternalID);
			put(PEREFERENCE_PROCESSOR, componentProcessors::processPEReference);
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
					put(CHAR_CHECKER, checkers::checkChar);
					put(NAMESTARTCHAR_CHECKER, checkers::checkNameStartChar);
					put(NAMECHAR_CHECKER, checkers::checkNameChar);
					put(NOTPERCENTANDSINGLEQUOTE_CHECKER, checkers::checkNotPercentAndSingleQuote);
					put(NOTPERCENTANDDOUBLEQUOTE_CHECKER, checkers::checkNotPercentAndDoubleQuote);
					put(NOTLOWERANDSINGLEQUOTE_CHECKER, checkers::checkNotLowerAndSingleQuote);
					put(NOTLOWERANDDOUBLEQUOTE_CHECKER, checkers::checkNotLowerAndDoubleQuote);
					put(NOTSINGLEQUOTE_CHECKER, checkers::checkNotSingleQuote);
					put(NOTDOUBLEQUOTE_CHECKER, checkers::checkNotDoubleQuote);
					put(PUBIDCHAR_CHECKER, checkers::checkPubidChar);
					put(NOTLOWERAND_CHECKER, checkers::checkNotLowerAnd);
					put(CHARDATA_CHECKER, checkers::checkCharData);
					put(CHARWITHOUTDASH_CHECKER, checkers::checkCharWithoutDash);
					put(PITARGET_CHECKER, checkers::checkPITarget);
					put(CHARWITHOUTPIEND_CHECKER, checkers::checkCharWithoutPIEnd);
					put(ENCNAMEFIRSTCHAR_CHECKER, checkers::checkEncNameFirstChar);
					put(ENCNAMECHAR_CHECKER, checkers::checkEncNameChar);
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
					put(PICHARS_RECOGNIZER, recognizers::recognizePIChars);
					put(CDATA_RECOGNIZER, recognizers::recognizeCData);
					put(IGNORE_RECOGNIZER, recognizers::recognizeIgnore);
				}
	};
	
	@Override
	public RecognizingFunction<Context, SourcecodeInputReader, Boolean> createRecognizer(String id) {
		RecognizingFunction<Context, SourcecodeInputReader, Boolean> result = RECOGNIZER_MAP.get(id);
		return result == null ? super.createRecognizer(id) : result;
	}
}
