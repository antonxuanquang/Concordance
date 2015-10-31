package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Interface.WordNode;
import Model.Concordance;
import View.ContextViewBuilder;
import View.Lab2View;

public class MainController implements ActionListener, MouseListener{
	
	private Lab2View view;
	private Concordance model;
	
	public MainController (Lab2View fromView) {
		view = fromView;
		model = view.model;
		
		setUpListeners();
	}
	
	
	public void loadCommonWords() {
		try {
			// model
			model.buildCommonWordsHash();
			//gui
			view.btnLoadCommonWords.setEnabled(false);
			view.btnBuildConcordance.setEnabled(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void buildConcordance() {
		try {
			// model
			model.buildConcordance();
			// gui
			updateListOfWordsAndCBOfFrequency(model.getTree());
			view.btnDisplayAll.setEnabled(true);
			view.btnGetBiggest.setEnabled(true);
			view.btnGetSmallest.setEnabled(true);
			view.btnFirst.setEnabled(true);
			view.btnPrevious.setEnabled(true);
			view.btnNext.setEnabled(true);
			view.btnLast.setEnabled(true);
			view.cbFrequency.setEnabled(true);
			view.tfSearch.setEnabled(true);
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	
	
	public void displayAllConcordance() {		
		view.resultPanel.removeAll();
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
			}
		} while (temp != tree);
		view.validate();
		view.repaint();		
	}

	private void updateListOfWordsAndCBOfFrequency(WordNode tree) {
		view.listOfWords.removeAllElements();
		WordNode temp = tree;
		ArrayList<Integer> frequencyArray = new ArrayList<Integer>();
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				updateListOfWords(temp);
				frequencyArray = updateArrayOfFrequency(temp, frequencyArray);
			}
		} while (temp != tree);
		updateCBOfFrequency(frequencyArray);		
	}

	private void updateListOfWords(WordNode temp) {
		view.listOfWords.addElement(temp.getWord());
	}

	private ArrayList<Integer> updateArrayOfFrequency(WordNode temp, ArrayList<Integer> array) {
		int count = temp.getCount();
		int index = Collections.binarySearch(array, count);
		if (index < 0) {
			array.add(-(index+1), count);
		}
		return array;
	}

	private void updateCBOfFrequency(ArrayList<Integer> frequencyArray) {
		view.cbFrequency.removeAllItems();
		view.cbFrequency.addItem("Choose a Frequency");
		for (int item: frequencyArray) {
			view.cbFrequency.addItem(item);
		}
	}
	
	
	private void displaySelectedWord() {
		view.resultPanel.removeAll();
		
		String word = (String) view.list.getSelectedValue();
		WordNode tree = model.getTree();
		WordNode temp = tree.getLeft();
		while (temp != tree) {
			if ((word.compareTo(temp.getWord()) < 0)) {
				temp = temp.getLeft();
			} else if ((word.compareTo(temp.getWord()) > 0)) {
				temp = temp.getRight();
			} else {
				ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
				view.validate();
				view.repaint();
				return;
			}
		}
	}

	private void displayFirstWord() {
		view.resultPanel.removeAll();
		
		String word = (String) view.listOfWords.getElementAt(0);
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				if (temp.getWord().equals(word)) {
					ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
					view.list.setSelectedIndex(0);
					view.validate();
					view.repaint();
					return;
				}
			}
		} while (temp != tree);
	}

	private void displayPreviousWord() {
		view.resultPanel.removeAll();
		
		int index = view.list.getSelectedIndex();
		if (index > 0) {
			view.list.setSelectedIndex(index - 1);
		} else {
			view.list.setSelectedIndex(0);
		}
		
		String word = (String) view.list.getSelectedValue();
		
		WordNode tree = model.getTree();
		WordNode temp = tree.getLeft();
		while (temp != tree) {
			if ((word.compareTo(temp.getWord()) < 0)) {
				temp = temp.getLeft();
			} else if ((word.compareTo(temp.getWord()) > 0)) {
				temp = temp.getRight();
			} else {
				ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
				view.validate();
				view.repaint();
				return;
			}
		}
	}
	
	private void displayNextWord() {
		view.resultPanel.removeAll();
		
		int index = view.list.getSelectedIndex();
		if (index > 0) {
			view.list.setSelectedIndex(index + 1);
		} else {
			view.list.setSelectedIndex(0);
		}
		
		String word = (String) view.list.getSelectedValue();
		
		WordNode tree = model.getTree();
		WordNode temp = tree.getLeft();
		while (temp != tree) {
			if ((word.compareTo(temp.getWord()) < 0)) {
				temp = temp.getLeft();
			} else if ((word.compareTo(temp.getWord()) > 0)) {
				temp = temp.getRight();
			} else {
				ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
				view.validate();
				view.repaint();
				return;
			}
		}
	}

	private void displayLastWord() {
		view.resultPanel.removeAll();
		int lastIndex = view.listOfWords.getSize() - 1;
		String word = (String) view.listOfWords.getElementAt(lastIndex);
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				if (temp.getWord().equals(word)) {
					ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
					view.list.setSelectedIndex(lastIndex);
					view.validate();
					view.repaint();	
					return;
				}
			}
		} while (temp != tree);
	}
	
	
	
	

	private void displayWordByFrequency() {
		
	}
	
	private void displayBiggest() {
		
	}


	private void displaySmallest() {
		
	}
	

	//set up Listeners
	

	
	void setUpListeners () {
		view.btnDisplayAll.addActionListener(this);
		view.btnGetBiggest.addActionListener(this);
		view.btnGetSmallest.addActionListener(this);
		view.btnLoadCommonWords.addActionListener(this);
		view.btnBuildConcordance.addActionListener(this);
		view.btnFirst.addActionListener(this);
		view.btnPrevious.addActionListener(this);
		view.btnNext.addActionListener(this);
		view.btnLast.addActionListener(this);
		view.cbFrequency.addActionListener(this);
		

		view.list.addMouseListener(this);
	}
	
	//invoke Action Listener 
	public void actionPerformed (ActionEvent e) {
		Object events = e.getSource();
		if (events.equals(view.btnLoadCommonWords)) loadCommonWords();
		else if (events.equals(view.btnBuildConcordance)) buildConcordance();
		else if (events.equals(view.btnDisplayAll)) displayAllConcordance();
		else if (events.equals(view.btnGetSmallest)) displaySmallest();
		else if (events.equals(view.btnGetBiggest)) displayBiggest();
		else if (events.equals(view.btnDisplayAll)) displayAllConcordance();
		else if (events.equals(view.btnFirst)) displayFirstWord();
		else if (events.equals(view.btnPrevious)) displayPreviousWord();
		else if (events.equals(view.btnNext)) displayNextWord();
		else if (events.equals(view.btnLast)) displayLastWord();
		else if (events.equals(view.cbFrequency)) displayWordByFrequency();
	}


	public void mouseClicked(MouseEvent e) {
		Object events = e.getSource();
		if (events.equals(view.list)) displaySelectedWord();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	//getters and setters
	public Lab2View getView() {
		return view;
	}
	
	
}
