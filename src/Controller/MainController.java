package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import Interface.WordNode;
import Model.Concordance;
import Theme.PrintASCII;
import View.ContextViewBuilder;
import View.Lab2View;

public class MainController implements ActionListener{
	
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
	
	private void updateListOfWordsAndCBOfFrequency(WordNode tree) {
		view.listOfWords.removeAllElements();
		ArrayList<Integer> frequencyArray = new ArrayList<Integer>();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				updateListOfWords(temp);
				updateArrayOfFrequency(temp, frequencyArray);
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

	
	
	private void updateListOfWords() {
		String searchKey = view.tfSearch.getText();
		System.out.println(searchKey);
		ArrayList<String> resultArray = new ArrayList<String>();
		
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				if (temp.getWord().contains(searchKey)) {
					resultArray.add(temp.getWord());
				}
			}
		} while (temp != tree);
		
//		update GUI
		view.listOfWords.removeAllElements();
		for (String value: resultArray) {
			view.listOfWords.addElement(value);
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
					view.list.ensureIndexIsVisible(view.list.getSelectedIndex());
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
				view.list.ensureIndexIsVisible(view.list.getSelectedIndex());
				view.validate();
				view.repaint();
				return;
			}
		}
	}
	
	private void displayNextWord() {
		view.resultPanel.removeAll();
		
		int index = view.list.getSelectedIndex();
		if (index >= 0) {
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
				view.list.ensureIndexIsVisible(view.list.getSelectedIndex());
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
					view.list.ensureIndexIsVisible(view.list.getSelectedIndex());
					view.validate();
					view.repaint();	
					return;
				}
			}
		} while (temp != tree);
	}
	
	
	
	
	private void displayWordByFrequency() {
		view.resultPanel.removeAll();
		
		Object items = view.cbFrequency.getSelectedItem();
		int frequency = 0;
		try {
			frequency = (int) items;
		} catch (Exception e) {}
		if (frequency == 0) return; 
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				if (temp.getCount() == frequency) {
					ContextViewBuilder.buildContextPanel(temp, view.resultPanel);
				}
			}
		} while (temp != tree);
		view.validate();
		view.repaint();
	}
	
	private void displayBiggest() {
		int lastIndex = view.cbFrequency.getItemCount() - 1;
		view.cbFrequency.setSelectedIndex(lastIndex);
	}

	private void displaySmallest() {
		view.cbFrequency.setSelectedIndex(1);
	}
	

	//set up Listeners
	
	
	
	
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
		
		view.tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateListOfWords();
			}
		});

		view.list.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		displaySelectedWord();
	    	}
	    });
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

	//getters and setters
	public Lab2View getView() {
		return view;
	}
	
	
	//for test class
	public WordNode getTree() {
		return model.getTree();
	}
}
