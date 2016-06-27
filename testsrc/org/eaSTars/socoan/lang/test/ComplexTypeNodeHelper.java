package org.eaSTars.socoan.lang.test;

import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.ComplexTypeNode;

public class ComplexTypeNodeHelper extends ComplexTypeNode {

	public ComplexTypeNodeHelper() {
	}
	
	public ComplexTypeNodeHelper(AbstractTypeDeclaration typedeclaration) {
		super.typeDeclaration = typedeclaration;
	}
	
	public void setTypeDeclaration(AbstractTypeDeclaration typedeclaration) {
		super.typeDeclaration = typedeclaration;
	}
}
