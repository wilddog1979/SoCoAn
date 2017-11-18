package org.eaSTars.socoaned;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyleConstants;
import javax.swing.text.Utilities;

public class TextLineNumber extends JPanel implements DocumentListener {

	private static final long serialVersionUID = -1951810449994133919L;

	public static enum Alignment {
		LEFT, CENTER, RIGHT;
	}

	private final static Border OUTER = new MatteBorder(0, 0, 0, 2, Color.GRAY);

	private final static int HEIGHT = Integer.MAX_VALUE - 1000000;

	private JTextComponent component;

	private boolean updateFont;
	private int borderGap;
	private Color currentLineForeground;
	private Alignment digitAlignment;
	private int minimumDisplayDigits;

	private int lastDigits;
	private int lastHeight;
	private int lastLine;

	private HashMap<String, FontMetrics> fonts;

	public TextLineNumber(JTextComponent component) {
		this.component = component;
		component.setText("");
		setFont(component.getFont());
		setBorderGap(5);
		setCurrentLineForeground(Color.RED);
		setDigitAlignment(Alignment.RIGHT);
		setMinimumDisplayDigits(3);

		component.getDocument().addDocumentListener(this);
		component.addCaretListener((e) -> {
			int caretPosition = component.getCaretPosition();
			Element root = component.getDocument().getDefaultRootElement();
			int currentLine = root.getElementIndex(caretPosition);

			//  Need to repaint so the correct line number can be highlighted

			if (lastLine != currentLine)
			{
				repaint();
				lastLine = currentLine;
			}
		});
		component.addPropertyChangeListener("font", (evt) -> {
			if (evt.getNewValue() instanceof Font)
			{
				if (updateFont)
				{
					Font newFont = (Font) evt.getNewValue();
					setFont(newFont);
					lastDigits = 0;
					setPreferredWidth();
				}
				else
				{
					repaint();
				}
			}
		});
	}

	public boolean isUpdateFont() {
		return updateFont;
	}

	public void setUpdateFont(boolean updateFont) {
		this.updateFont = updateFont;
	}

	public int getBorderGap() {
		return borderGap;
	}

	public void setBorderGap(int borderGap)
	{
		this.borderGap = borderGap;
		Border inner = new EmptyBorder(0, borderGap, 0, borderGap);
		setBorder(new CompoundBorder(OUTER, inner));
		lastDigits = 0;
		setPreferredWidth();
	}

	public Color getCurrentLineForeground() {
		return currentLineForeground == null ? getForeground() : currentLineForeground;
	}

	public void setCurrentLineForeground(Color currentLineForeground) {
		this.currentLineForeground = currentLineForeground;
	}

	public Alignment getDigitAlignment() {
		return digitAlignment;
	}

	public void setDigitAlignment(Alignment digitAlignment) {
		this.digitAlignment = digitAlignment;
	}

	public int getMinimumDisplayDigits() {
		return minimumDisplayDigits;
	}

	public void setMinimumDisplayDigits(int minimumDisplayDigits)
	{
		this.minimumDisplayDigits = minimumDisplayDigits;
		setPreferredWidth();
	}

	private void setPreferredWidth()
	{
		Element root = component.getDocument().getDefaultRootElement();
		int lines = root.getElementCount();
		int digits = Math.max(String.valueOf(lines).length(), minimumDisplayDigits);

		//  Update sizes when number of digits in the line number changes

		if (lastDigits != digits)
		{
			lastDigits = digits;
			FontMetrics fontMetrics = getFontMetrics(getFont());
			int width = fontMetrics.charWidth('0') * digits;
			Insets insets = getInsets();
			int preferredWidth = insets.left + insets.right + width;

			Dimension d = getPreferredSize();
			d.setSize(preferredWidth, HEIGHT);
			setPreferredSize(d);
			setSize(d);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//	Determine the width of the space available to draw the line number

		FontMetrics fontMetrics = component.getFontMetrics(component.getFont());
		Insets insets = getInsets();
		int availableWidth = getSize().width - insets.left - insets.right;

		//  Determine the rows to draw within the clipped bounds.

		Rectangle clip = g.getClipBounds();
		int rowStartOffset = component.viewToModel(new Point(0, clip.y));
		int endOffset = component.viewToModel(new Point(0, clip.y + clip.height));

		while (rowStartOffset <= endOffset)
		{
			try
			{
				if (isCurrentLine(rowStartOffset)) {
					g.setColor(getCurrentLineForeground());
				} else {
					g.setColor(getForeground());
				}

				//  Get the line number as a string and then determine the
				//  "X" and "Y" offsets for drawing the string.

				String lineNumber = getTextLineNumber(rowStartOffset);
				int stringWidth = fontMetrics.stringWidth(lineNumber);
				int x = getOffsetX(availableWidth, stringWidth) + insets.left;
				int y = getOffsetY(rowStartOffset, fontMetrics);
				g.drawString(lineNumber, x, y);

				//  Move to the next row

				rowStartOffset = Utilities.getRowEnd(component, rowStartOffset) + 1;
			}
			catch(Exception e) {break;}
		}
	}

	private boolean isCurrentLine(int rowStartOffset)
	{
		int caretPosition = component.getCaretPosition();
		Element root = component.getDocument().getDefaultRootElement();

		return root.getElementIndex(rowStartOffset) == root.getElementIndex(caretPosition);
	}

	protected String getTextLineNumber(int rowStartOffset)
	{
		Element root = component.getDocument().getDefaultRootElement();
		int index = root.getElementIndex(rowStartOffset);
		Element line = root.getElement(index);

		if (line.getStartOffset() == rowStartOffset)
			return String.valueOf(index + 1);
		else
			return "";
	}

	private int getOffsetX(int availableWidth, int stringWidth)
	{
		int result = availableWidth - stringWidth;

		switch(digitAlignment) {
		case LEFT:
			result *= -1;
			break;
		case RIGHT:
			result *= 1;
			break;
		case CENTER:
			break;
		}

		return result;
	}

	private int getOffsetY(int rowStartOffset, FontMetrics fontMetrics)
			throws BadLocationException
	{
		//  Get the bounding rectangle of the row

		Rectangle r = component.modelToView(rowStartOffset);
		int lineHeight = fontMetrics.getHeight();
		int y = r.y + r.height;
		int descent = 0;

		//  The text needs to be positioned above the bottom of the bounding
		//  rectangle based on the descent of the font(s) contained on the row.

		if (r.height == lineHeight)  // default font is being used
		{
			descent = fontMetrics.getDescent();
		}
		else  // We need to check all the attributes for font changes
		{
			if (fonts == null) {
				fonts = new HashMap<String, FontMetrics>();
			}

			Element root = component.getDocument().getDefaultRootElement();
			int index = root.getElementIndex(rowStartOffset);
			Element line = root.getElement(index);

			for (int i = 0; i < line.getElementCount(); i++)
			{
				Element child = line.getElement(i);
				AttributeSet as = child.getAttributes();
				String fontFamily = (String)as.getAttribute(StyleConstants.FontFamily);
				Integer fontSize = (Integer)as.getAttribute(StyleConstants.FontSize);
				String key = fontFamily + fontSize;

				FontMetrics fm = fonts.get(key);

				if (fm == null)
				{
					Font font = new Font(fontFamily, Font.PLAIN, fontSize);
					fm = component.getFontMetrics(font);
					fonts.put(key, fm);
				}

				descent = Math.max(descent, fm.getDescent());
			}
		}

		return y - descent;
	}

	// Implement DocumentListener interface

	@Override
	public void changedUpdate(DocumentEvent e) {
		documentChanged();
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		documentChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		documentChanged();
	}

	private void documentChanged()
	{
		//  View of the component has not been updated at the time
		//  the DocumentEvent is fired

		SwingUtilities.invokeLater(() ->
		{
			try
			{
				int endPos = component.getDocument().getLength();
				Rectangle rect = component.modelToView(endPos);

				if (rect != null && rect.y != lastHeight)
				{
					setPreferredWidth();
					repaint();
					lastHeight = rect.y;
				}
			}
			catch (BadLocationException ex) { /* nothing to do */ }
		});
	}
}
