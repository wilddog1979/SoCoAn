package org.eaSTars.socoaned;

import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Language;

public class TypeListEntry implements Comparable<TypeListEntry>{

	private Language language;
	
	private AbstractTypeDeclaration typeDeclaration;

	public TypeListEntry(Language language, AbstractTypeDeclaration type) {
		this.language = language;
		this.typeDeclaration = type;
	}
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public AbstractTypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	public void setTypeDeclaration(AbstractTypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
	}
	
	@Override
	public String toString() {
		return typeDeclaration.getId();
	}

	@Override
	public int compareTo(TypeListEntry o) {
		if (o == null) {
			return -1;
		}
		return getTypeDeclaration().getId().toLowerCase()
				.compareTo(o.getTypeDeclaration().getId().toLowerCase());
	}
}
