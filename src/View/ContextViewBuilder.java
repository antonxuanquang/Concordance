package View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import Interface.ContextNode;
import Interface.WordNode;
import Theme.ColorTheme;
import Theme.QLabel;
import net.miginfocom.swing.MigLayout;

public class ContextViewBuilder {
	
	public ContextViewBuilder() {}
	
	public static void buildContextPanel(WordNode node, JPanel resultPanel) {
		//column 1
		JTextArea leftTA = new JTextArea();
		leftTA.append("-" + node.getWord() + "\n");
		leftTA.append("-Number of occurrence: " + node.getCount());
		setUpTextArea(leftTA, ColorTheme.getSecondaryColor());
		resultPanel.add(leftTA, "sg left, aligny top");
		
		
		//column 2
		JTextArea rightTA = new JTextArea();
		
		ContextNode temp = node.getContextLink();
		while (temp != null) {
			rightTA.append("-Context: " + temp.getContext() + "\n");
			rightTA.append("-Paragraph Number: " + temp.getParagraphNum() + "\n");
			rightTA.append("-Sentence Number: " + temp.getSentenceNum() + "\n");
			rightTA.append("\n");
			temp = temp.getNext();
		}
		setUpTextArea(rightTA, ColorTheme.getPrimaryColor());
		resultPanel.add(rightTA, "growx, pushx, wrap");
	}

	private static void setUpTextArea(JTextArea ta, Color color) {
		ta.setEditable(false);
		ta.setOpaque(false);
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setForeground(color);
	}
}