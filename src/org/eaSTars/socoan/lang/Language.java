package org.eaSTars.socoan.lang;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.eaSTars.socoan.lang.base.Literal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Language")
public class Language {

	@XmlElements({
		@XmlElement(name = "ComplexNode", type = ComplexNode.class),
        @XmlElement(name = "Literal", type = Literal.class)
    })
	protected List<LanguageNode> nodeTypes;
	
	public List<LanguageNode> getNoteTypes() {
        if (nodeTypes == null) {
        	nodeTypes = new ArrayList<LanguageNode>();
        }
        return nodeTypes;
    }
}
