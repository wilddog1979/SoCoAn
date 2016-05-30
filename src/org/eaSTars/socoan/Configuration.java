package org.eaSTars.socoan;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eaSTars.socoan.config.ObjectFactory;
import org.eaSTars.socoan.config.SoCoAnConfigType;

public class Configuration {

	private SoCoAnConfigType config = new SoCoAnConfigType();
	
	public boolean processArguments(String[] args) {
		boolean result = true;
		
		int i = 0;
		for (;i < args.length; ++i) {
			if (!args[i].startsWith("-")) {
				break;
			}
			
		}
		if (result &= i == args.length - 1) {
			File file = new File(args[i]);
			if (result &= file.exists()) {
				result = loadConfig(file);
			}
		}
		
		return result;
	}
	
	public boolean loadConfig(File file) {
		boolean result = false;
		
		try {
			JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<SoCoAnConfigType> doc = (JAXBElement<SoCoAnConfigType>) unmarshaller.unmarshal(file);
			config = doc.getValue();
			
			result = true;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean saveConfig(File file) {
		boolean result = false;
		
		try {
			JAXBContext context = JAXBContext.newInstance(SoCoAnConfigType.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(new ObjectFactory().createSoCoAnConfig(config), file);
			
			result = true;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public SoCoAnConfigType getConfig() {
		return config;
	}
}
