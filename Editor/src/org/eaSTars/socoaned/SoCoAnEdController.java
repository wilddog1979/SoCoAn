package org.eaSTars.socoaned;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.eaSTars.socoan.lang.AbstractTypeDeclaration;
import org.eaSTars.socoan.lang.Language;
import org.eaSTars.socoan.lang.ReferenceNotFoundException;

import com.apple.eawt.Application;

public class SoCoAnEdController implements SoCoAnEdDelegate {

	private static final String APP_NAME = "SoCoAnEd";

	private SoCoAnEdFrame frame;
	
	private List<LanguageController> languageControllers =
			new Vector<LanguageController>();
	
	private DefaultListModel<FileListEntry> filelistmodel =
			new DefaultListModel<FileListEntry>();
	
	private List<TypeListEntry> typelist = new Vector<TypeListEntry>();
	
	public SoCoAnEdController() {
		initSettings();
		
		frame = new SoCoAnEdFrame(this);
		frame.setFileListModel(filelistmodel);
		frame.setTypeList(typelist);
		frame.setVisible(true);
		
		try {
			loadLangaugeFile(new File("../JavaExtension/resources/JavaLang.xml"));
			frame.setTypeList(typelist);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReferenceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initSettings() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("mac") != -1) {
			System.setProperty("apple.awt.graphics.EnableQ2DX", "true");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty(
					"com.apple.mrj.application.apple.menu.about.name",
					APP_NAME);

			Application macApplication = Application.getApplication();
			macApplication.setAboutHandler(paramAboutEvent -> {});
			macApplication.setPreferencesHandler(paramPreferencesEvent -> {});
			
			macApplication.setQuitHandler((quitevent, quitresponse) -> {
				if (confirmExit()) {
					quitresponse.performQuit();
				} else {
					quitresponse.cancelQuit();
				}
			});
		} else if (os.indexOf("windows") != -1) {
			
		}
	}

	private boolean confirmExit() {
		return JOptionPane.showConfirmDialog(
				frame,
				String.format("Do you want to exit from %s?", APP_NAME),
				"Exit",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
				) == JOptionPane.YES_OPTION;
	}
	
	@Override
	public void exitApplication() {
		if (confirmExit()) {
			System.exit(0);
		}
	}
	
	@Override
	public void openFile() {
		JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileFilter(new SoCoAnFileFilter());
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			closeFiles();
			try {
				loadLangaugeFile(fileChooser.getSelectedFile());
			} catch (JAXBException | ReferenceNotFoundException e) {
				JOptionPane.showMessageDialog(
						frame,
						e.getClass().getName()+": "+e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private int addNewController(FileListEntry entry)
			throws TransformerFactoryConfigurationError, TransformerException, IOException,
			JAXBException, ReferenceNotFoundException {
		LanguageController languageController =
				new LanguageController(entry.getFile());
		languageControllers.add(languageController);
		
		frame.addLanguageTab(
				entry.toString(), languageController.getLanguagePane());
		
		return languageControllers.size() - 1;
	}
	
	@Override
	public void openLanguageFile(FileListEntry entry) {
		Optional<Integer> ctrlIndex = languageControllers.stream()
		.filter(languageController ->
			languageController.getFile().equals(entry.getFile()))
		.findFirst()
		.map(languageController ->
			languageControllers.indexOf(languageController));
		
		if (ctrlIndex.isPresent()) {
			frame.activateLanguageTab(ctrlIndex.get());
		} else {
			try {
				frame.activateLanguageTab(addNewController(entry));
			} catch (TransformerFactoryConfigurationError |
					TransformerException | IOException | JAXBException | ReferenceNotFoundException e) {
				JOptionPane.showMessageDialog(frame,
						String.format("The file could not be loaded\n(%s)\n%s",
								entry.getFile().getAbsolutePath()),
						e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void addFileListEntry(String name, File file) {
		filelistmodel.addElement(new FileListEntry(name, file));
	}
	
	private void addTypeListEntry(Language language,
			AbstractTypeDeclaration type) {
		typelist.add(new TypeListEntry(language, type));
	}
	
	private void loadLangaugeFile(File file)
			throws JAXBException, ReferenceNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Language.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Language> doc = unmarshaller.unmarshal(new StreamSource(file), Language.class);
		Language language = doc.getValue();	
		language.setFilename(file.getAbsolutePath());
		addFileListEntry(file.getName(), file);
		language.resolveFileReferences(
				Optional.of((f, l) -> addFileListEntry(file.getParentFile()
						.toURI().relativize(f.toURI()).getPath(), f)),
				file.getParentFile());
		language.resolveNodeReferences(
				Optional.of((l, t) -> addTypeListEntry(l, t)),
				null);
	}
	
	@Override
	public void closeFiles() {
		System.out.println("Close");
	}
}
