package View;

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
	
	public static JPanel buildContextPanel(WordNode node) {
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new MigLayout("insets 0 0 5 0"));
		JPanel leftPanel = buildLeftSide(node);
		JPanel rightPanel = buildRightSide(node);
		
		resultPanel.add(leftPanel, "sg left, aligny top");
		resultPanel.add(rightPanel, "growx, pushx");
		
		return resultPanel;
	}

	private static JPanel buildLeftSide(WordNode temp) {
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new MigLayout("", "[]", "[]"));
		
		QLabel word = new QLabel(temp.getWord());
		QLabel count = new QLabel ("Number of occurrence: " + temp.getCount());
		
		resultPanel.add(word, "wrap");
		resultPanel.add(count, "wrap");
		
		return resultPanel;
		
	}

	public static JPanel buildRightSide(WordNode node) {
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new MigLayout("", "[]", "[]"));
		String context = "";
		
		ContextNode temp = node.getContextLink();
		while (temp != null) {			
			context += "-Context: " + temp.getContext() + "\n";
			context += "-Paragraph Number: " + temp.getParagraphNum() + "\n";
			context += "-Sentence Number: " + temp.getSentenceNum() + "\n";
			context += "\n";
			temp = temp.getNext();
		}
		JTextArea ta = new JTextArea(context);
		ta.setEditable(false);
		ta.setOpaque(false);
		ta.setLineWrap(true);
		ta.setForeground(ColorTheme.getPrimaryColor());
		resultPanel.add(ta, "growx, pushx");
		return resultPanel; 
	}
	
	
}

class ContextBuilder {
	public ContextBuilder() {}
	
	
}