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
import View.ContextViewBuilder;
import View.Lab2View;

/**
 * This is the main controller of this application. The flow of my program:
 * <ol> 
 * <li> Action Events detect which component is triggered </li>
 * <li> Invoke a method corresponding to that event. </li>
 * <li> Methods underneath invoked method are related. The nearer to invoked method, the more important it is </li>
 * </ol>
 */

public class MainController implements ActionListener{
	
	private Lab2View view;
	private Concordance model;
	
	
	/**
	 * 
	 * When creating a new instance of this class, this constructor will:
	 * 		1. Try to gain access to model and view
	 * 		2. set up Listeners for all component in the view  
	 * 
	 * @param fromView the view this class control
	 */
	public MainController (Lab2View fromView) {
		view = fromView;
		model = view.model;
		
		setUpListeners();
	}
	
	/**
	 * 1. Invoked when user clicks on Load Common Words File button
	 * 2. Check whether the program is about to remove or load a file 
	 */
	private void loadCommonWords() {
		if (view.btnLoadCommonWords.getText().equals("Load Common Words File")) {
			addCommonWords();
		} else {
			removeCommonWords();
		}
		
	}
	
	/**
	 * Invoke buildCommonWordsHash method in model, then change the GUI accordingly.
	 * Warn users the failure of loading a file if they don't want to do so. 
	 */
	private void addCommonWords() {
		try {
			// model
			model.buildCommonWordsHash();
			//gui
			view.btnLoadCommonWords.setText("Remove Common Words File");
			view.btnBuildConcordance.setEnabled(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}		
	}

	/**
	 * Invoke resetCommonWordsHashTable method in model, then change the GUI accordingly.
	 */
	private void removeCommonWords() {
		model.resetCommonWordsHashTable();
		view.btnLoadCommonWords.setText("Load Common Words File");
	}

	
	/**
	 * Invoke buildConcordance method in model, then change the GUI accordingly 
	 * 		(i.e. update List of Words and JComboBox of Frequency, enable buttons). 
	 * Warn users the failure of loading a file if they don't want to do so. 
	 */
	private void buildConcordance() {
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
			view.btnDelete.setEnabled(true);
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * 
	 * While trying to traverse all the tree, this method attempt to:
	 * 		1. adding words into List in GUI
	 * 		2. adding unique frequency into Array of Frequency
	 * Then, add all items in Array of Frequency to JComboBox of Frequency 
	 * 
	 * @param tree the link to head of concordance tree
	 */
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
	
	/**
	 * 
	 * Adding words into List in GUI
	 * 
	 * @param temp currently visiting Word Node
	 */
	private void updateListOfWords(WordNode temp) {
		view.listOfWords.addElement(temp.getWord());
	}

	
	/**
	 * 
	 * Collections.binarySearch return a positive integer if the item is found in the Array or
	 * 		negative index in sorted position if the item is not found.
	 * 
	 * @param temp currently visiting Word Node
	 * @param array Array of Frequency
	 * @return updated and sorted Array of Frequency
	 */
	private ArrayList<Integer> updateArrayOfFrequency(WordNode temp, ArrayList<Integer> array) {
		int count = temp.getCount();
		int index = Collections.binarySearch(array, count);
		if (index < 0) {
			array.add(-(index+1), count);
		}
		return array;
	}

	/**
	 * 
	 * Loop through items in the array then add them to the JComboBox in GUI.
	 * 
	 * @param frequencyArray Array of Frequency
	 */
	private void updateCBOfFrequency(ArrayList<Integer> frequencyArray) {
		view.cbFrequency.removeAllItems();
		view.cbFrequency.addItem("Choose a Frequency");
		for (int item: frequencyArray) {
			view.cbFrequency.addItem(item);
		}
	}
	
	
	
	/**
	 * While traversing concordance tree, invoke method buildContextPanel in ContextViewBuilder to add 
	 * 	visiting Word Node's content into GUI.
	 */
	private void displayAllConcordance() {
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

	
	/**
	 * While traversing concordance word tree, compare visiting Word Node's word with the input, then
	 * 		add those words that start with the input string. 
	 * 
	 * Comment: This method is using a poor algorithm because it searches through all the tree rather than a portion of it.
	 */
	private void updateListOfWords() {
		String searchKey = view.tfSearch.getText();
		ArrayList<String> resultArray = new ArrayList<String>();
		
		WordNode tree = model.getTree();
		WordNode temp = tree;
		do {
			temp = tree.findInOrderSuccessor(temp);
			if (temp != tree) {
				if (temp.getWord().startsWith(searchKey.toLowerCase())) {
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
	
	
	/**
	 * 1. Getting the value of selected word
	 * 2. Moving through the word tree by comparing the value and the word of visiting Word Node.
	 * 3. If the value is less than Word Node's word, then move to the left, and vice versa for greater than situation.
	 * 
	 * Since the list consists of all the words in program, it is guaranteed that the context of the word is found is found.
	 */
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

	/**
	 * Looking for the value of the first item in JComboBox. Since JComboBox consists of all sorted possible 
	 * 		frequency in the word tree, the first item is a good result.
	 * Traversing the word tree and look for Word Node that have the same frequency, then add the context to Panel of Result. 
	 */
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
	
	/**
	 * 1. Getting the value of selected word
	 * 2. Moving through the word tree by comparing the value and the word of visiting Word Node.
	 * 3. If the value is less than Word Node's word, then move to the left, and vice versa for greater than situation.
	 * 
	 * Since the list consists of all the words in program, it is guaranteed that the context of the word is found is found.
	 */
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
	
	/**
	 * 1. Getting the value of selected word
	 * 2. Moving through the word tree by comparing the value and the word of visiting Word Node.
	 * 3. If the value is less than Word Node's word, then move to the left, and vice versa for greater than situation.
	 * 
	 * Since the list consists of all the words in program, it is guaranteed that the context of the word is found is found.
	 */
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
	
	/**
	 * Looking for the value of the last item in JComboBox. Since JComboBox consists of all sorted possible 
	 * 		frequency in the word tree, the last item is a good result.
	 * Traversing the word tree and look for Word Node that have the same frequency, then add the context to Panel of Result. 
	 */
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
	
	
	
	/**
	 * 
	 * Get the value of frequency being selected
	 * Traversing the word tree and look for Word Node that have the same frequency, then add the context to Panel of Result. 
	 */
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
	
	/**
	 * Have the program selected the last item, which then trigger displayWordByFrequency method
	 */
	private void displayBiggest() {
		int lastIndex = view.cbFrequency.getItemCount() - 1;
		view.cbFrequency.setSelectedIndex(lastIndex);
	}

	/**
	 * Have the program selected the second item (because first item is an informative item, not a frequency), 
	 * 		which then trigger displayWordByFrequency method
	 */
	private void displaySmallest() {
		view.cbFrequency.setSelectedIndex(1);
	}
	

	private void deleteAWord() {
		String word = (String) view.list.getSelectedValue();
		
		WordNode tree = model.getTree();
		WordNode temp = tree.getLeft();
		WordNode successor = tree;
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
	
	
	/**
	 * Add all Listeners to GUI components
	 */
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
		view.btnDelete.addActionListener(this);
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
	
	/**
	 * Invoke methods when user click on buttons
	 */
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
		else if (events.equals(view.btnDelete)) deleteAWord();
	}

}
