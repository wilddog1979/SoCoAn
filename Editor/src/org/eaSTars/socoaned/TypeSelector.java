package org.eaSTars.socoaned;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class TypeSelector extends JComboBox<TypeListEntry>{

	private static final long serialVersionUID = 7621288525925593828L;

	private DefaultComboBoxModel<TypeListEntry> typesmodel =
			new DefaultComboBoxModel<TypeListEntry>();
	
	private List<TypeListEntry> types;

	private boolean backspace = false;
	private boolean backspaceonselection = false;
	
	public TypeSelector() {
		setModel(typesmodel);
		setEditable(true);

		JTextComponent editor =
				(JTextComponent)getEditor().getEditorComponent();
		editor.setDocument(new PlainDocument(){

			private static final long serialVersionUID = 1476501960015267956L;

			private boolean selecting = false;
			
			@Override
			public void insertString(int offs, String str, AttributeSet a)
					throws BadLocationException {
				super.insertString(offs, str, a);
				if (!selecting) {
					String content = getText(0, getLength());
					updateContent(content);
				}
			}
			
			@Override
			public void remove(int offs, int len) throws BadLocationException {
				super.remove(offs, len);
				if (backspace) {
					backspace = false;
					String content = getText(0, getLength() -
							(backspaceonselection && getLength() > 0 ? 1 : 0));
					updateContent(content);
				}
			}
			
			private void updateContent(String content) {
				selecting = true;
				filterlist(content);
				if (typesmodel.getSize() != 0) {
					setSelectedIndex(0);
					editor.setCaretPosition(content.length());
					editor.moveCaretPosition(content.length());
					editor.setSelectionStart(content.length());
					editor.setSelectionEnd(getLength());
				} else {
					editor.setText(content);
				}
				selecting = false;
			}
		});
		
		editor.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					backspace = true;
					backspaceonselection =
						editor.getSelectionStart() != editor.getSelectionEnd() &&
						editor.getSelectionEnd() == editor.getDocument().getLength();
				}
			}
		});
	}
	
	private void filterlist(String text) {
		final String mask = text.toLowerCase();
		typesmodel.removeAllElements();
		types.stream()
			.filter(type -> type.getTypeDeclaration().getId()
					.toLowerCase().startsWith(mask))
			.forEach(type ->
				typesmodel.insertElementAt(type, typesmodel.getSize()));
	}
	
	public List<TypeListEntry> getTypes() {
		if (types == null) {
			types = new Vector<TypeListEntry>();
		}
		return types;
	}

	public void setTypes(List<TypeListEntry> types) {
		this.types = types;
		
		Collections.sort(this.types);
		
		types.forEach(type ->
			typesmodel.insertElementAt(type, typesmodel.getSize()));
	}
}
