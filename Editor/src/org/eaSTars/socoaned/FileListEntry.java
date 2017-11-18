package org.eaSTars.socoaned;

import java.io.File;

public class FileListEntry {

	private String name;
	
	private File file;
	
	public FileListEntry(String name, File file) {
		this.name = name;
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return name;
	}
}
