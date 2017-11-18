package org.eaSTars.socoaned;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;

public class SoCoAnEdFrame extends JFrame
	implements WindowListener, MouseListener {

	private static final long serialVersionUID = -1556112022871073764L;

	private JMenuItem menuitemOpen = createMenuItem("Open...", 'O');
	private JMenuItem menuitemClose = createMenuItem("Close", 'C');
	private JMenu menuFile = createMenu("File",
			menuitemOpen, menuitemClose);
	
	private JMenuItem menuitemStartDebug = createMenuItem("Start", "F11");
	private JMenuItem menuitemResumeDebug = createMenuItem("Resume", "F8");
	private JMenuItem menuitemStopDebug = createMenuItem("Stop", "F2");
	private JMenuItem menuitemStepInto = createMenuItem("Step Into", "F5");
	private JMenuItem menuitemStepOver = createMenuItem("Step Over", "F6");
	private JMenu menuDebug = createMenu("Debug", menuitemStartDebug,
			menuitemResumeDebug, menuitemStopDebug, menuitemStepInto,
			menuitemStepOver);
	
	private SoCoAnEdDelegate delegate;
	
	private JList<FileListEntry> filelist = new JList<FileListEntry>();
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	private JLabel starting = new JLabel("Starting:");
	
	private TypeSelector types = new TypeSelector();
	
	private JTextArea sampletext = new JTextArea(4, 1);
	
	private JButton processSample = new JButton("Process");
	
	public SoCoAnEdFrame(SoCoAnEdDelegate delegate) {
		this.delegate = delegate;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		Container cnt = getContentPane();
		
		JSplitPane mainsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		mainsplitpane.setLeftComponent(
				new JScrollPane(
						filelist,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)
				);
		filelist.addMouseListener(this);
		
		JPanel rightpane = new JPanel(new BorderLayout());
		
		rightpane.add(tabbedPane, BorderLayout.CENTER);
		
		rightpane.add(buildSamplePane(), BorderLayout.SOUTH);
		
		mainsplitpane.setRightComponent(rightpane);
		
		cnt.add(mainsplitpane, BorderLayout.CENTER);
		
		cnt.add(buildStatusBar(), BorderLayout.SOUTH);
		
		buildMenu();
		
		setSize(640, 480);
	}
	
	public void setFileListModel(ListModel<FileListEntry> model) {
		filelist.setModel(model);
	}
	
	public void setTypeList(List<TypeListEntry> typelist) {
		types.setTypes(typelist);
	}
	
	private JPanel buildSamplePane() {
		JPanel samplepane = new JPanel(new BorderLayout());
		samplepane.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JPanel startingpane = new JPanel(new BorderLayout());
		startingpane.add(starting, BorderLayout.WEST);
		types.addActionListener(ae ->
			debugControl(types.getSelectedItem() != null));
		startingpane.add(types, BorderLayout.CENTER);
		samplepane.add(startingpane, BorderLayout.NORTH);
		
		samplepane.add(
				new JScrollPane(
						sampletext,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
				BorderLayout.CENTER);
		
		JPanel buttonpanel = new JPanel();
		processSample.setEnabled(false);
		buttonpanel.add(processSample);
		samplepane.add(buttonpanel, BorderLayout.EAST);
		
		return samplepane;
	}
	
	private void debugControl(boolean control) {
		processSample.setEnabled(control);
		menuitemStartDebug.setEnabled(control);
	}
	
	private void buildMenu() {
		JMenuBar menubar = new JMenuBar();
		
		menubar.add(menuFile);
		menuitemOpen.addActionListener(ae -> delegate.openFile());
		menuitemClose.addActionListener(ae -> delegate.closeFiles());
		menubar.add(menuDebug);
		menuitemStartDebug.setEnabled(false);
		menuitemResumeDebug.setEnabled(false);
		menuitemStopDebug.setEnabled(false);
		menuitemStepInto.setEnabled(false);
		menuitemStepOver.setEnabled(false);
		
		setJMenuBar(menubar);
	}
	
	private JMenuItem createMenuItem(String name) {
		return new JMenuItem(name);
	}
	
	private JMenuItem createMenuItem(String name, char shortcut) {
		JMenuItem menuitem = createMenuItem(name);
		
		menuitem.setAccelerator(
				KeyStroke.getKeyStroke(
						shortcut,
						Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()
						)
				);
		
		return menuitem;
	}
	
	private JMenuItem createMenuItem(String name, String shortcut) {
		JMenuItem menuitem = createMenuItem(name);
		
		menuitem.setAccelerator(KeyStroke.getKeyStroke(shortcut));
		
		return menuitem;
	}
	
	/*
	private JMenuItem createMenuItem(String name, char shortcut,
		int modifiermask) {
		JMenuItem menuitem = createMenuItem(name);
		
		menuitem.setAccelerator(
				KeyStroke.getKeyStroke(
						shortcut,
						Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() |
							modifiermask
						)
				);
		
		return menuitem;
	}
	*/
	
	private JMenu createMenu(String name, JMenuItem...items) {
		JMenu menu = new JMenu(name);
		
		Arrays.asList(items).forEach(item ->{
			if (item instanceof JMenuItemSeparator) {
				menu.addSeparator();
			} else {
				menu.add(item);
			}
		});
		
		return menu;
	}
	
	private JPanel buildStatusBar() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		panel.add(new JLabel("Status:"));
		
		return panel;
	}
	
	public void addLanguageTab(String name, JTextPane languagePane) {
		JScrollPane scrollpane = new JScrollPane(languagePane);
		TextLineNumber tln = new TextLineNumber(languagePane);
		scrollpane.setRowHeaderView(tln);
		
		tabbedPane.addTab(name, scrollpane);
	}
	
	public void activateLanguageTab(int index) {
		tabbedPane.setSelectedIndex(index);
	}

	@Override
	public void windowOpened(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void windowClosing(WindowEvent paramWindowEvent) {
		delegate.exitApplication();
	}

	@Override
	public void windowClosed(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void windowIconified(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void windowActivated(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent paramWindowEvent) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == filelist && e.getButton() == MouseEvent.BUTTON1
				&& e.getClickCount() == 2) {
			delegate.openLanguageFile(filelist.getSelectedValue());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
