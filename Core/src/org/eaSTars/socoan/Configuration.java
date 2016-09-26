package org.eaSTars.socoan;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.config.ObjectFactory;
import org.eaSTars.socoan.config.SoCoAnConfig;

public class Configuration {

	private SoCoAnConfig config = new SoCoAnConfig();
	
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
			JAXBContext context = JAXBContext.newInstance(SoCoAnConfig.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<SoCoAnConfig> doc = unmarshaller.unmarshal(new StreamSource(file), SoCoAnConfig.class);
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
			JAXBContext context = JAXBContext.newInstance(SoCoAnConfig.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(new ObjectFactory().createSoCoAnConfig(config), file);
			
			result = true;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public SoCoAnConfig getConfig() {
		return config;
	}
}
