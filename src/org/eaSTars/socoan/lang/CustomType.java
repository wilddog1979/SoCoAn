package org.eaSTars.socoan.lang;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;

import org.eaSTars.socoan.SourcecodeInputStream;

public class CustomType extends AbstractTypeDeclaration {

	@XmlAttribute(name="recognizer")
	private String recognizer;
	
	private Language parent;
	
	public String getRecognizer() {
		return recognizer;
	}

	@Override
	public void resolveNodeReferences(Language parent) throws ReferenceNotFoundException {
		this.parent = parent;
		if (parent.getProcessorFactory().createRecognizer(recognizer) == null) {
			throw new ReferenceNotFoundException("recognizer", recognizer);
		}
	}
	
	@Override
	public boolean recognizeType(Context context, SourcecodeInputStream sis) throws IOException {
		boolean result = false;
		try {
			result = parent.getProcessorFactory().createRecognizer(recognizer).apply(context, sis);
		} catch (WrappedIOException e) {
			throw e.getIoexception();
		}
		return result;
	}

}
