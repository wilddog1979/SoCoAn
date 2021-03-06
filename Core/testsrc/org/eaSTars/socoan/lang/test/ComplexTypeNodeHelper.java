package org.eaSTars.socoan.lang.test;

import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.ComplexTypeNode;
import org.eaSTars.socoan.lang.Occurrence;
import org.eaSTars.socoan.lang.Sequence;

public class ComplexTypeNodeHelper extends ComplexTypeNode {

	public ComplexTypeNodeHelper() {
	}
	
	public ComplexTypeNodeHelper(AbstractTypeDeclaration typedeclaration) {
		super.typeDeclaration = typedeclaration;
	}
	
	public void setTypeDeclaration(AbstractTypeDeclaration typedeclaration) {
		super.typeDeclaration = typedeclaration;
	}
	
	public void setOccurrence(Occurrence occurrence) {
		super.occurrence = occurrence;
	}
	
	public void setSequence(Sequence sequence) {
		super.sequence = sequence;
	}
}
