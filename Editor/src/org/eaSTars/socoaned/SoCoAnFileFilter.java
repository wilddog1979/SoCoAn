package org.eaSTars.socoaned;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SoCoAnFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return f.getName().endsWith(".xml");
	}

	@Override
	public String getDescription() {
		return "SoCoAn descriptor file (*.xml)";
	}

}
