package org.eaSTars;

import javax.swing.SwingUtilities;

import org.eaSTars.socoaned.SoCoAnEdController;

public class SoCoAnEd {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SoCoAnEdController());
	}
}
